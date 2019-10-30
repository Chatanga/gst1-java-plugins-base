package org.freedesktop.gstreamer.video;

import org.freedesktop.gstreamer.lowlevel.video.GstVideoInfoAPI.GstVideoFormatInfoStruct;

import com.sun.jna.Pointer;

public class VideoFormatInfo {

	private GstVideoFormatInfoStruct struct;

	VideoFormatInfo(Pointer pointer) {
		this.struct = new GstVideoFormatInfoStruct(pointer);
	}

	public VideoFormat getFormat() {
		return struct.format;
	}

}
