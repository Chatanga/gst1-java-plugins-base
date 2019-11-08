package org.freedesktop.gstreamer.gl;

import org.freedesktop.gstreamer.Buffer;
import org.freedesktop.gstreamer.Caps;
import org.freedesktop.gstreamer.glib.Natives;
import org.freedesktop.gstreamer.lowlevel.GstBufferAPI;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLMemoryAPI;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoFrameAPI;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoFrameAPI.GstVideoFrameStruct;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoInfoPtr;
import org.freedesktop.gstreamer.video.VideoFrame;
import org.freedesktop.gstreamer.video.VideoInfo;

import com.sun.jna.Pointer;

/**
 * A video frame to map a buffer.
 */
public class GLVideoFrame {

    private final GstVideoFrameStruct struct;

    private int[] textures;

    public GLVideoFrame() {
        struct = new GstVideoFrameStruct();
    }

    public boolean map(Buffer buffer, Caps caps) {
        VideoInfo info = new VideoInfo(caps);
        map(info, buffer);
        return textures != null;
    }

    /**
     * Map a GL memory buffer into a GL video frame. It relies on the general video
     * frame mapping (actually hidden in {@link Buffer#map(boolean) and also in
     * {@link VideoFrame} but assuming it's a GL buffer.
     */
    public boolean map(VideoInfo info, Buffer buffer) {
        if (textures == null) {
            int flags = GstBufferAPI.GST_MAP_READ | GstGLMemoryAPI.GST_MAP_GL;
            GstVideoInfoPtr gstVideoInfoPtr = Natives.getPointer(info).as(GstVideoInfoPtr.class, GstVideoInfoPtr::new);
            if (GstVideoFrameAPI.GSTVIDEOFRAME_API.gst_video_frame_map(struct, gstVideoInfoPtr, buffer, flags)) {
                textures = new int[buffer.getMemoryCount()];
                for (int i = 0; i < textures.length; ++i) {
                    Pointer p = struct.data[i];
                    textures[i] = p != null ? p.getInt(0) : 0;
                }
                return true;
            } else {
                return false;
            }
        } else {
            throw new IllegalStateException("GL video frame is already mapped.");
        }
    }

    /**
     * Unmap the memory previously mapped.
     */
    public void unmap() {
        if (textures != null) {
            GstVideoFrameAPI.GSTVIDEOFRAME_API.gst_video_frame_unmap(struct);
            /*
             * The 'unmap' will unref the buffer (if it did it in the 'map' in accordance to
             * the GST_VIDEO_FRAME_MAP_FLAG_NO_REF flag) and this struct is not expected to
             * keep a ref on it.
             */
            struct.buffer.disown();
            textures = null;
        } else {
            throw new IllegalStateException("GL video frame is not mapped.");
        }
    }

    public int getTextureId(int index) {
        if (textures != null) {
            return textures[index];
        } else {
            throw new IllegalStateException("GL video frame is not mapped.");
        }
    }

    public int[] getTextures() {
        if (textures != null) {
            return textures;
        } else {
            throw new IllegalStateException("GL video frame is not mapped.");
        }
    }

}
