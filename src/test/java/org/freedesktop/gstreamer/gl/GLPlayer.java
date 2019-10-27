package org.freedesktop.gstreamer.gl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Version;

public class GLPlayer {

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		String progName = GLPlayer.class.getSimpleName();

		SwingUtilities.invokeAndWait(() -> {
			Gst.setUseDefaultContext(false);
			Gst.init(new Version(1, 6), progName, "--gst-debug=*:1");

			GLVideoCanvas glVideoCanvas = new GLVideoCanvas();
			glVideoCanvas.setPreferredSize(new Dimension(320, 240));

			JFrame frame = new JFrame(progName);
			frame.setLayout(new BorderLayout());
			frame.add(glVideoCanvas);
			frame.pack();
			frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			frame.setLocationRelativeTo(null);

			frame.addComponentListener(new ComponentAdapter() {

				/*
				 * We wait that the frame has shown to be sure that JOGL has created its
				 * context.
				 */
				@Override
				public void componentShown(ComponentEvent e) {
					frame.removeComponentListener(this);

					// To be able to retrieve it in GStreamer.
					glVideoCanvas.getContext().makeCurrent();

					GLDisplay glDisplay = getGLDisplay();
					GLContext glContext = getGLContext(glDisplay);

					// To avoid locking it in Swing.
					glVideoCanvas.getContext().release();

					VideoPipeline videoPipeline = new VideoPipeline(glVideoCanvas.getElement(), glDisplay, glContext);
					videoPipeline.getPipe().play();
				}
			});

			frame.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent windowevent) {
					frame.remove(glVideoCanvas);
					frame.dispose();
					System.exit(0);
				}
			});

			frame.setVisible(true);
		});
	}

	/*
	 * Creating a new display. Reusing the existing application display (like in the
	 * 'sdlshare.c' example) doesn’t seem necessary.
	 */
	private static GLDisplay getGLDisplay() {
		return new GLDisplay();
	}

	/*
	 * Wraps the current GL context to allow GStreamer to create a shared context
	 * with it.
	 */
	private static GLContext getGLContext(GLDisplay glDisplay) {
		EnumSet<GLAPI> apis = EnumSet.of(GLAPI.forName(System.getProperty("GST_GL_API", "opengl")));
		GLPlatform platform = GLPlatform.forName(System.getProperty("GST_GL_PLATFORM", "glx"));
		long handle = GLContext.getCurrentGLContext(platform);
		if (handle == 0) {
			throw new AssertionError("No current GL context.");
		}
		return new GLWrappedContext(glDisplay, handle, platform, apis);
	}
}
