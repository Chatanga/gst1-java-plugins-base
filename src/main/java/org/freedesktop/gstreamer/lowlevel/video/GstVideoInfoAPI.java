package org.freedesktop.gstreamer.lowlevel.video;

import java.util.Arrays;
import java.util.List;

import org.freedesktop.gstreamer.Caps;
import org.freedesktop.gstreamer.lowlevel.GstAPI;
import org.freedesktop.gstreamer.lowlevel.GstNative;
import org.freedesktop.gstreamer.lowlevel.annotations.CallerOwnsReturn;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoColorAPI.GstVideoColorimetryStruct;
import org.freedesktop.gstreamer.video.VideoFormat;
import org.freedesktop.gstreamer.video.VideoInfo;

import com.sun.jna.Pointer;

/*
 * https://gstreamer.freedesktop.org/documentation/video/video-info.html
 * 
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/video/video-info.h
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/video/video-info.c
 */
public interface GstVideoInfoAPI extends com.sun.jna.Library {

	int GST_VIDEO_MAX_PLANES = 4;

	int GST_VIDEO_MAX_COMPONENTS = 4;

	GstVideoInfoAPI GSTVIDEOINFO_API = GstNative.load("gstvideo", GstVideoInfoAPI.class);

	public static final class GstVideoFormatInfoStruct extends com.sun.jna.Structure {

		public GstVideoFormatInfoStruct(Pointer ptr) {
			super(ptr);
			read();
		}

		public volatile VideoFormat format;
		public volatile Pointer /* String */ name;
		public volatile Pointer /* String */ description;
		public volatile int /* GstVideoFormatFlags */ flags;
		public volatile int bits;
		public volatile int n_components;
		public volatile int[] shift = new int[GST_VIDEO_MAX_COMPONENTS];
		public volatile int[] depth = new int[GST_VIDEO_MAX_COMPONENTS];
		public volatile int[] pixel_stride = new int[GST_VIDEO_MAX_COMPONENTS];
		public volatile int n_planes;
		public volatile int[] plane = new int[GST_VIDEO_MAX_COMPONENTS];
		public volatile int[] poffset = new int[GST_VIDEO_MAX_COMPONENTS];
		public volatile int[] w_sub = new int[GST_VIDEO_MAX_COMPONENTS];
		public volatile int[] h_sub = new int[GST_VIDEO_MAX_COMPONENTS];

		public volatile int /* GstVideoFormat */ unpack_format;
		public volatile Pointer /* GstVideoFormatUnpack */ unpack_func;
		public volatile int pack_lines;
		public volatile Pointer /* GstVideoFormatPack */ pack_func;

		public volatile int /* GstVideoTileMode */ tile_mode;
		public volatile int tile_ws;
		public volatile int tile_hs;

		public volatile AnonymousUnion anonymousUnion;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("format", "name", "description", "flags", "bits", "n_components", "shift", "depth",
					"pixel_stride", "n_planes", "plane", "poffset", "w_sub", "h_sub", "unpack_format", "unpack_func",
					"pack_lines", "pack_func", "tile_mode", "tile_ws", "tile_hs", "anonymousUnion");
		}

		public static final class AnonymousUnion extends com.sun.jna.Union {

			public volatile ABI abi;

			/* < private > */
			public volatile Pointer[] _gst_reserved = new Pointer[GstAPI.GST_PADDING];

			@Override
			protected List<String> getFieldOrder() {
				return Arrays.asList("abi", "_gst_reserved");
			}

			public static final class ABI extends com.sun.jna.Structure {

				public volatile int /* GstVideoMultiviewMode */ multiview_mode;
				public volatile int /* GstVideoMultiviewFlags */ multiview_flags;
				public volatile int /* GstVideoFieldOrder */ field_order;

				@Override
				protected List<String> getFieldOrder() {
					return Arrays.asList("multiview_mode", "multiview_flags", "field_order");
				}
			}
		}
	}

	public static final class GstVideoInfoStruct extends com.sun.jna.Structure {

		public GstVideoInfoStruct(Pointer ptr) {
			super(ptr);
			read();
		}

		public volatile Pointer /* GstVideoFormatInfo */ finfo;

		public volatile int /* GstVideoInterlaceMode */ interlace_mode;
		public volatile int /* GstVideoFlags */ flags;
		public volatile int width;
		public volatile int height;
		public volatile long size;
		public volatile int views;

		public volatile int /* GstVideoChromaSite */ chroma_site;
		public volatile GstVideoColorimetryStruct colorimetry;

		public volatile int par_n;
		public volatile int par_d;
		public volatile int fps_n;
		public volatile int fps_d;

		public volatile long[] offset = new long[GST_VIDEO_MAX_PLANES];
		public volatile int[] stride = new int[GST_VIDEO_MAX_PLANES];

		/* < private > */
		public volatile Pointer[] _gst_reserved = new Pointer[GstAPI.GST_PADDING];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("finfo", "interlace_mode", "flags", "width", "height", "size", "views", "chroma_site",
					"colorimetry", "par_n", "par_d", "fps_n", "fps_d", "offset", "stride", "_gst_reserved");
		}
	}

	@CallerOwnsReturn GstVideoInfoPtr gst_video_info_new();

	void gst_video_info_free(GstVideoInfoPtr info);

	boolean gst_video_info_set_format(GstVideoInfoPtr info, VideoFormat format, int width, int height);

	void gst_video_info_init(VideoInfo info);

	boolean gst_video_info_from_caps(GstVideoInfoPtr info, Caps caps);

}
