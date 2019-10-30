package org.freedesktop.gstreamer.video;

import org.freedesktop.gstreamer.Buffer;
import org.freedesktop.gstreamer.glib.Natives;
import org.freedesktop.gstreamer.lowlevel.GstBufferAPI;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoFrameAPI;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoFrameAPI.GstVideoFrameStruct;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoInfoPtr;

import com.sun.jna.Pointer;

public class VideoFrame {

    private final GstVideoFrameStruct struct;

    public VideoFrame() {
        struct = new GstVideoFrameStruct();
    }

    public Pointer[] map(VideoInfo info, Buffer buffer, boolean writable) {
        GstVideoInfoPtr gstVideoInfoPtr = Natives.getPointer(info).as(GstVideoInfoPtr.class, GstVideoInfoPtr::new);
        int flags = writable ? GstBufferAPI.GST_MAP_WRITE : GstBufferAPI.GST_MAP_READ;
        if (GstVideoFrameAPI.GSTVIDEOFRAME_API.gst_video_frame_map(struct, gstVideoInfoPtr, buffer, flags)) {
            return struct.data;
        } else {
            return null;
        }
    }

    public void unmap() {
        GstVideoFrameAPI.GSTVIDEOFRAME_API.gst_video_frame_unmap(struct);
    }

    public Pointer[] getData() {
        return struct.data;
    }

}
