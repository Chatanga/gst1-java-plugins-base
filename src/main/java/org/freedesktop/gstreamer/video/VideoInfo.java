package org.freedesktop.gstreamer.video;

import org.freedesktop.gstreamer.Caps;
import org.freedesktop.gstreamer.MiniObject;
import org.freedesktop.gstreamer.lowlevel.GPointer;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoInfoAPI;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoInfoAPI.GstVideoInfoStruct;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoInfoPtr;

public class VideoInfo extends MiniObject {

    public static final String GTYPE_NAME = "GstVideoInfo";

    private final Handle handle;

    private final GstVideoInfoStruct struct;

    /**
     * Creates a newly allocated video info with default values.
     */
    public VideoInfo() {
        this(new Handle(GstVideoInfoAPI.GSTVIDEOINFO_API.gst_video_info_new(), true), true);
    }

    /**
     * Creates a newly allocated video info with default values.
     */
    public VideoInfo(Caps caps) {
        this();
        setCaps(caps);
    }

    VideoInfo(Handle handle, boolean needRef) {
        super(handle, needRef);
        this.handle = handle;
        this.struct = new GstVideoInfoStruct(handle.getPointer().getPointer());
    }

    VideoInfo(Initializer init) {
        this(new Handle(init.ptr.as(GstVideoInfoPtr.class, GstVideoInfoPtr::new), init.ownsHandle), init.needRef);
    }

    public boolean setCaps(Caps caps) {
        if (GstVideoInfoAPI.GSTVIDEOINFO_API.gst_video_info_from_caps(handle.getPointer(), caps)) {
            struct.read();
            return true;
        }
        return false;
    }

    public VideoFormatInfo getFormatInfo() {
        return new VideoFormatInfo(struct.finfo);
    }

    public int getFlags() {
        return struct.flags;
    }

    public int getWidth() {
        return struct.width;
    }

    public int getHeight() {
        return struct.height;
    }

    private static final class Handle extends MiniObject.Handle {

        public Handle(GstVideoInfoPtr ptr, boolean ownsHandle) {
            super(ptr, ownsHandle);
        }

        @Override
        protected GstVideoInfoPtr getPointer() {
            return (GstVideoInfoPtr) super.getPointer();
        }

        @Override
        protected void disposeNativeHandle(GPointer ptr) {
            GstVideoInfoAPI.GSTVIDEOINFO_API.gst_video_info_free((GstVideoInfoPtr) ptr);
        }

    }

}
