package org.freedesktop.gstreamer.gl;

import java.util.concurrent.atomic.AtomicReference;

import org.freedesktop.gstreamer.Buffer;
import org.freedesktop.gstreamer.Caps;
import org.freedesktop.gstreamer.Element;
import org.freedesktop.gstreamer.FlowReturn;
import org.freedesktop.gstreamer.Sample;
import org.freedesktop.gstreamer.elements.AppSink;
import org.freedesktop.gstreamer.gl.GLVideoFrame;
import org.freedesktop.gstreamer.video.VideoFormat;
import org.freedesktop.gstreamer.video.VideoInfo;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

@SuppressWarnings("serial")
public class GLVideoCanvas extends GLCanvas {

	private final AppSink videosink;

	private final AtomicReference<GLVideoFrame> frameDataRef = new AtomicReference<>();

	public GLVideoCanvas() {
		super(new GLCapabilities(GLProfile.getDefault()));

		this.videosink = new AppSink(GLVideoCanvas.class.getSimpleName());
		videosink.set("emit-signals", true);

		AppSinkListener listener = new AppSinkListener();
		videosink.connect((AppSink.NEW_SAMPLE) listener);
		videosink.connect((AppSink.NEW_PREROLL) listener);

		videosink.setCaps(new Caps("video/x-raw(memory:GLMemory)"));

		addGLEventListener(new GLEventListener() {

			@Override
			public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
			}

			@Override
			public void init(GLAutoDrawable glautodrawable) {
			}

			@Override
			public void dispose(GLAutoDrawable glautodrawable) {
			}

			@Override
			public void display(GLAutoDrawable glautodrawable) {
				GLVideoFrame glVideoFrame = frameDataRef.getAndSet(null);
				if (glVideoFrame != null) {
					GL2 gl = glautodrawable.getGL().getGL2();

					renderRGB(gl, glVideoFrame.getTextures()[0]);

					gl.glFlush();
					glVideoFrame.unmap();
				}
			}

			private void renderRGB(GL2 gl, int texture) {

				gl.glMatrixMode(GL2.GL_PROJECTION);
				gl.glLoadIdentity();
				gl.glOrtho(0, 1, 1, 0, -1, 1);

				gl.glEnable(GL2.GL_TEXTURE_2D);
				gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);

				gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
				gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);

				gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);

				gl.glBegin(GL2.GL_QUADS);
				{
					gl.glTexCoord2i(0, 0);
					gl.glVertex2i(0, 0);

					gl.glTexCoord2i(1, 0);
					gl.glVertex2i(1, 0);

					gl.glTexCoord2i(1, 1);
					gl.glVertex2i(1, 1);

					gl.glTexCoord2i(0, 1);
					gl.glVertex2i(0, 1);
				}
				gl.glEnd();
			}
		});
	}

	public Element getElement() {
		return videosink;
	}

	private class AppSinkListener implements AppSink.NEW_SAMPLE, AppSink.NEW_PREROLL {

		@Override
		public FlowReturn newSample(AppSink elem) {
			return onNewSample(elem.pullSample(), false);
		}

		@Override
		public FlowReturn newPreroll(AppSink elem) {
			return onNewSample(elem.pullPreroll(), true);
		}

		public FlowReturn onNewSample(Sample sample, boolean preroll) {
			Buffer buffer = sample.getBuffer();
			try (VideoInfo info = VideoInfo.createFromCaps(sample.getCaps())) {
				VideoFormat format = info.getFormatInfo().getFormat();
				onNewGlBuffer(buffer, info, format);
			}
			sample.dispose();
			return FlowReturn.OK;
		}

		private void onNewGlBuffer(Buffer buffer, VideoInfo info, VideoFormat format) {
			GLVideoFrame frame = new GLVideoFrame();
			if (frame.map(info, buffer) != null) {
				switch (format) {
				case RGB:
					if (frame.getTextures().length != 1) {
						throw new AssertionError("Unexpected number of textures!");
					}
					GLVideoFrame unusedFrameData = frameDataRef.getAndSet(frame);
					if (unusedFrameData != null) {
						System.out.println("Dropping frame.");
						unusedFrameData.unmap();
					}
					repaint();
					break;
				default:
					System.err.println("Unhandled video format: " + format);
					frame.unmap();
					break;
				}
			} else {
				System.err.println("Unmappable frame!");
				frame.unmap(); // Useful?
			}
		}

	}
}
