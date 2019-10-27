package org.freedesktop.gstreamer.lowlevel.video;

import java.util.Arrays;
import java.util.List;

public class GstVideoColorAPI {

	public static final class GstVideoColorimetryStruct extends com.sun.jna.Structure {

		/**
		 * Possible color range values. These constants are defined for 8 bit color
		 * values and can be scaled for other bit depths.
		 * 
		 * @GST_VIDEO_COLOR_RANGE_UNKNOWN: unknown range
		 * @GST_VIDEO_COLOR_RANGE_0_255: [0..255] for 8 bit components
		 * @GST_VIDEO_COLOR_RANGE_16_235: [16..235] for 8 bit components. Chroma has
		 *                                [16..240] range.
		 */
		public volatile int /* GstVideoColorRange */ range;

		/**
		 * The color matrix is used to convert between Y'PbPr and non-linear RGB
		 * (R'G'B')
		 * 
		 * @GST_VIDEO_COLOR_MATRIX_UNKNOWN: unknown matrix
		 * @GST_VIDEO_COLOR_MATRIX_RGB: identity matrix
		 * @GST_VIDEO_COLOR_MATRIX_FCC: FCC color matrix
		 * @GST_VIDEO_COLOR_MATRIX_BT709: ITU-R BT.709 color matrix
		 * @GST_VIDEO_COLOR_MATRIX_BT601: ITU-R BT.601 color matrix
		 * @GST_VIDEO_COLOR_MATRIX_SMPTE240M: SMPTE 240M color matrix
		 * @GST_VIDEO_COLOR_MATRIX_BT2020: ITU-R BT.2020 color matrix. Since: 1.6
		 */
		public volatile int /* GstVideoColorMatrix */ matrix;

		/**
		 * The video transfer function defines the formula for converting between
		 * non-linear RGB (R'G'B') and linear RGB
		 * 
		 * @GST_VIDEO_TRANSFER_UNKNOWN: unknown transfer function
		 * @GST_VIDEO_TRANSFER_GAMMA10: linear RGB, gamma 1.0 curve
		 * @GST_VIDEO_TRANSFER_GAMMA18: Gamma 1.8 curve
		 * @GST_VIDEO_TRANSFER_GAMMA20: Gamma 2.0 curve
		 * @GST_VIDEO_TRANSFER_GAMMA22: Gamma 2.2 curve
		 * @GST_VIDEO_TRANSFER_BT709: Gamma 2.2 curve with a linear segment in the lower
		 *                            range
		 * @GST_VIDEO_TRANSFER_SMPTE240M: Gamma 2.2 curve with a linear segment in the
		 *                                lower range
		 * @GST_VIDEO_TRANSFER_SRGB: Gamma 2.4 curve with a linear segment in the lower
		 *                           range
		 * @GST_VIDEO_TRANSFER_GAMMA28: Gamma 2.8 curve
		 * @GST_VIDEO_TRANSFER_LOG100: Logarithmic transfer characteristic 100:1 range
		 * @GST_VIDEO_TRANSFER_LOG316: Logarithmic transfer characteristic 316.22777:1
		 *                             range
		 * @GST_VIDEO_TRANSFER_BT2020_12: Gamma 2.2 curve with a linear segment in the
		 *                                lower range. Used for BT.2020 with 12 bits per
		 *                                component. Since: 1.6
		 * @GST_VIDEO_TRANSFER_ADOBERGB: Gamma 2.19921875. Since: 1.8
		 */
		public volatile int /* GstVideoTransferFunction */ transfer;

		/**
		 * The color primaries define the how to transform linear RGB values to and from
		 * the CIE XYZ colorspace.
		 * 
		 * @GST_VIDEO_COLOR_PRIMARIES_UNKNOWN: unknown color primaries
		 * @GST_VIDEO_COLOR_PRIMARIES_BT709: BT709 primaries
		 * @GST_VIDEO_COLOR_PRIMARIES_BT470M: BT470M primaries
		 * @GST_VIDEO_COLOR_PRIMARIES_BT470BG: BT470BG primaries
		 * @GST_VIDEO_COLOR_PRIMARIES_SMPTE170M: SMPTE170M primaries
		 * @GST_VIDEO_COLOR_PRIMARIES_SMPTE240M: SMPTE240M primaries
		 * @GST_VIDEO_COLOR_PRIMARIES_FILM: Generic film
		 * @GST_VIDEO_COLOR_PRIMARIES_BT2020: BT2020 primaries. Since: 1.6
		 * @GST_VIDEO_COLOR_PRIMARIES_ADOBERGB: Adobe RGB primaries. Since: 1.8
		 * @GST_VIDEO_COLOR_PRIMARIES_SMPTEST428: SMPTE ST 428 primaries. Since: 1.16
		 * @GST_VIDEO_COLOR_PRIMARIES_SMPTERP431: SMPTE RP 431 primaries. Since: 1.16
		 * @GST_VIDEO_COLOR_PRIMARIES_SMPTEEG432: SMPTE EG 432 primaries. Since: 1.16
		 * @GST_VIDEO_COLOR_PRIMARIES_EBU3213: EBU 3213 primaries. Since: 1.16
		 */
		public volatile int /* GstVideoColorPrimaries */ primaries;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("range", "matrix", "transfer", "primaries");
		}
	}
}
