package org.freedesktop.gstreamer.video;

import org.freedesktop.gstreamer.Caps;
import org.freedesktop.gstreamer.glib.NativeObject;
import org.freedesktop.gstreamer.glib.Natives;
import org.freedesktop.gstreamer.lowlevel.GPointer;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoInfoAPI;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoInfoAPI.GstVideoInfoStruct;

public class VideoInfo extends NativeObject {

	public static final String GTYPE_NAME = "GstVideoInfo";

	public static VideoInfo create() {
		return GstVideoInfoAPI.GSTVIDEOINFO_API.gst_video_info_new();
	}

	public static VideoInfo createFromCaps(Caps caps) {
		VideoInfo videoInfo = GstVideoInfoAPI.GSTVIDEOINFO_API.gst_video_info_new();
		if (videoInfo.setCaps(caps)) {
			return videoInfo;
		} else {
			return null;
		}
	}

	private GstVideoInfoStruct struct;

	/**
	 * Creates a newly allocated video info with default values.
	 */
	public VideoInfo() {
		this(Natives.initializer(GstVideoInfoAPI.GSTVIDEOINFO_API.ptr_gst_video_info_new()));
	}

	/**
	 * This constructor is for internal use only.
	 * 
	 * @param init initialization data.
	 */
	protected VideoInfo(Initializer init) {
		super(new Handle(init.ptr, init.ownsHandle));
		this.struct = new GstVideoInfoStruct(init.ptr.getPointer());
	}

	public boolean setCaps(Caps caps) {
		if (GstVideoInfoAPI.GSTVIDEOINFO_API.gst_video_info_from_caps(this, caps)) {
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

	private static final class Handle extends NativeObject.Handle {

		public Handle(GPointer ptr, boolean ownsHandle) {
			super(ptr, ownsHandle);
		}

		@Override
		protected void disposeNativeHandle(GPointer ptr) {
			GstVideoInfoAPI.GSTVIDEOINFO_API.gst_video_info_free(ptr.getPointer());
		}
	}
}
