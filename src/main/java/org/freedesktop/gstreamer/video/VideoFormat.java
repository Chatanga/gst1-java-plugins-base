package org.freedesktop.gstreamer.video;

import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.glib.NativeEnum;

/**
 * Enum value describing the most common video formats.
 *
 * See the [GStreamer raw video format design
 * document](https://gstreamer.freedesktop.org/documentation/design/mediatype-video-raw.html#formats)
 * for details about the layout and packing of these formats in memory.
 */
public enum VideoFormat implements NativeEnum<VideoFormat> {

    /** Unknown or unset video format id */
    UNKNOWN,
    /**
     * Encoded video format. Only ever use that in caps for special video formats in
     * combination with non-system memory GstCapsFeatures where it does not make
     * sense to specify a real video format.
     */
    ENCODED,
    /** planar 4:2:0 YUV */
    I420,
    /** planar 4:2:0 YVU (like I420 but UV planes swapped) */
    YV12,
    /** packed 4:2:2 YUV (Y0-U0-Y1-V0 Y2-U2-Y3-V2 Y4 ...) */
    YUY2,
    /** packed 4:2:2 YUV (U0-Y0-V0-Y1 U2-Y2-V2-Y3 U4 ...) */
    UYVY,
    /** packed 4:4:4 YUV with alpha channel (A0-Y0-U0-V0 ...) */
    AYUV,
    /** sparse rgb packed into 32 bit, space last */
    RGBx,
    /** sparse reverse rgb packed into 32 bit, space last */
    BGRx,
    /** sparse rgb packed into 32 bit, space first */
    xRGB,
    /** sparse reverse rgb packed into 32 bit, space first */
    xBGR,
    /** rgb with alpha channel last */
    RGBA,
    /** reverse rgb with alpha channel last */
    BGRA,
    /** rgb with alpha channel first */
    ARGB,
    /** reverse rgb with alpha channel first */
    ABGR,
    /** RGB packed into 24 bits without padding (`R-G-B-R-G-B`) */
    RGB,
    /** reverse RGB packed into 24 bits without padding (`B-G-R-B-G-R`) */
    BGR,
    /** planar 4:1:1 YUV */
    Y41B,
    /** planar 4:2:2 YUV */
    Y42B,
    /** packed 4:2:2 YUV (Y0-V0-Y1-U0 Y2-V2-Y3-U2 Y4 ...) */
    YVYU,
    /** planar 4:4:4 YUV */
    Y444,
    /** packed 4:2:2 10-bit YUV, complex format */
    v210,
    /** packed 4:2:2 16-bit YUV, Y0-U0-Y1-V1 order */
    v216,
    /** planar 4:2:0 YUV with interleaved UV plane */
    NV12,
    /** planar 4:2:0 YUV with interleaved VU plane */
    NV21,
    /** 8-bit grayscale */
    GRAY8,
    /** 16-bit grayscale, most significant byte first */
    GRAY16_BE,
    /** 16-bit grayscale, least significant byte first */
    GRAY16_LE,
    /** packed 4:4:4 YUV (Y-U-V ...) */
    v308,
    /** rgb 5-6-5 bits per component */
    RGB16,
    /** reverse rgb 5-6-5 bits per component */
    BGR16,
    /** rgb 5-5-5 bits per component */
    RGB15,
    /** reverse rgb 5-5-5 bits per component */
    BGR15,
    /** packed 10-bit 4:2:2 YUV (U0-Y0-V0-Y1 U2-Y2-V2-Y3 U4 ...) */
    UYVP,
    /** planar 4:4:2:0 AYUV */
    A420,
    /** 8-bit paletted RGB */
    RGB8P,
    /** planar 4:1:0 YUV */
    YUV9,
    /** planar 4:1:0 YUV (like YUV9 but UV planes swapped) */
    YVU9,
    /** packed 4:1:1 YUV (Cb-Y0-Y1-Cr-Y2-Y3 ...) */
    IYU1,
    /** rgb with alpha channel first, 16 bits per channel */
    ARGB64,
    /**
     * packed 4:4:4 YUV with alpha channel, 16 bits per channel (A0-Y0-U0-V0 ...)
     */
    AYUV64,
    /** packed 4:4:4 RGB, 10 bits per channel */
    r210,
    /** planar 4:2:0 YUV, 10 bits per channel */
    I420_10BE,
    /** planar 4:2:0 YUV, 10 bits per channel */
    I420_10LE,
    /** planar 4:2:2 YUV, 10 bits per channel */
    I422_10BE,
    /** planar 4:2:2 YUV, 10 bits per channel */
    I422_10LE,
    /** planar 4:4:4 YUV, 10 bits per channel */
    @Gst.Since(minor = 2)
    Y444_10BE,
    /** planar 4:4:4 YUV, 10 bits per channel */
    @Gst.Since(minor = 2)
    Y444_10LE,
    /** planar 4:4:4 RGB, 8 bits per channel */
    @Gst.Since(minor = 2)
    GBR,
    /** planar 4:4:4 RGB, 10 bits per channel */
    @Gst.Since(minor = 2)
    GBR_10BE,
    /** planar 4:4:4 RGB, 10 bits per channel */
    @Gst.Since(minor = 2)
    GBR_10LE,
    /** planar 4:2:2 YUV with interleaved UV plane */
    @Gst.Since(minor = 2)
    NV16,
    /** planar 4:4:4 YUV with interleaved UV plane */
    @Gst.Since(minor = 2)
    NV24,
    /** NV12 with 64x32 tiling in zigzag pattern */
    @Gst.Since(minor = 4)
    NV12_64Z32,
    /** planar 4:4:2:0 YUV, 10 bits per channel */
    @Gst.Since(minor = 6)
    A420_10BE,
    /** planar 4:4:2:0 YUV, 10 bits per channel */
    @Gst.Since(minor = 6)
    A420_10LE,
    /** planar 4:4:2:2 YUV, 10 bits per channel */
    @Gst.Since(minor = 6)
    A422_10BE,
    /** planar 4:4:2:2 YUV, 10 bits per channel */
    @Gst.Since(minor = 6)
    A422_10LE,
    /** planar 4:4:4:4 YUV, 10 bits per channel */
    @Gst.Since(minor = 6)
    A444_10BE,
    /** planar 4:4:4:4 YUV, 10 bits per channel */
    @Gst.Since(minor = 6)
    A444_10LE,
    /** planar 4:2:2 YUV with interleaved VU plane */
    @Gst.Since(minor = 6)
    NV61,
    /**
     * planar 4:2:0 YUV with interleaved UV plane, 10 bits per channel
     */
    @Gst.Since(minor = 10)
    P010_10BE,
    /**
     * planar 4:2:0 YUV with interleaved UV plane, 10 bits per channel
     */
    @Gst.Since(minor = 10)
    P010_10LE,
    /** packed 4:4:4 YUV (U-Y-V ...) */
    @Gst.Since(minor = 10)
    IYU2,
    /** packed 4:2:2 YUV (V0-Y0-U0-Y1 V2-Y2-U2-Y3 V4 ...) */
    VYUY,
    /** planar 4:4:4:4 ARGB, 8 bits per channel */
    @Gst.Since(minor = 12)
    GBRA,
    /** planar 4:4:4:4 ARGB, 10 bits per channel */
    @Gst.Since(minor = 12)
    GBRA_10BE,
    /** planar 4:4:4:4 ARGB, 10 bits per channel */
    @Gst.Since(minor = 12)
    GBRA_10LE,
    /** planar 4:4:4 RGB, 12 bits per channel */
    @Gst.Since(minor = 12)
    GBR_12BE,
    /** planar 4:4:4 RGB, 12 bits per channel */
    @Gst.Since(minor = 12)
    GBR_12LE,
    /** planar 4:4:4:4 ARGB, 12 bits per channel */
    @Gst.Since(minor = 12)
    GBRA_12BE,
    /** planar 4:4:4:4 ARGB, 12 bits per channel */
    @Gst.Since(minor = 12)
    GBRA_12LE,
    /** planar 4:2:0 YUV, 12 bits per channel */
    @Gst.Since(minor = 12)
    I420_12BE,
    /** planar 4:2:0 YUV, 12 bits per channel */
    @Gst.Since(minor = 12)
    I420_12LE,
    /** planar 4:2:2 YUV, 12 bits per channel */
    @Gst.Since(minor = 12)
    I422_12BE,
    /** planar 4:2:2 YUV, 12 bits per channel */
    @Gst.Since(minor = 12)
    I422_12LE,
    /** planar 4:4:4 YUV, 12 bits per channel */
    @Gst.Since(minor = 12)
    Y444_12BE,
    /** planar 4:4:4 YUV, 12 bits per channel */
    @Gst.Since(minor = 12)
    Y444_12LE,

    /** 10-bit grayscale, packed into 32bit words (2 bits padding) */
    @Gst.Since(minor = 14)
    GRAY10_LE32,
    /**
     * 10-bit variant of @NV12, packed into 32bit words (MSB 2 bits padding)
     */
    @Gst.Since(minor = 14)
    NV12_10LE32,
    /**
     * 10-bit variant of @NV16, packed into 32bit words (MSB 2 bits padding)
     */
    @Gst.Since(minor = 14)
    NV16_10LE32,
    /** Fully packed variant of NV12_10LE32 */
    @Gst.Since(minor = 16)
    NV12_10LE40,
    /** packed 4:2:2 YUV, 10 bits per channel */
    @Gst.Since(minor = 16)
    Y210,
    /** packed 4:4:4 YUV, 10 bits per channel(A-V-Y-U...) */
    @Gst.Since(minor = 16)
    Y410,
    /** packed 4:4:4 YUV with alpha channel (V0-U0-Y0-A0...) */
    @Gst.Since(minor = 16)
    VUYA,
    /**
     * packed 4:4:4 RGB with alpha channel(B-G-R-A), 10 bits for R/G/B channel and
     * MSB 2 bits for alpha channel
     */
    @Gst.Since(minor = 16)
    BGR10A2_LE,
    /**
     * packed 4:4:4 RGB with alpha channel(R-G-B-A), 10 bits for R/G/B channel and
     * MSB 2 bits for alpha channel
     */
    @Gst.Since(minor = 16)
    RGB10A2_LE,
    /** planar 4:4:4 YUV, 16 bits per channel */
    @Gst.Since(minor = 18)
    Y444_16BE,
    /** planar 4:4:4 YUV, 16 bits per channel */
    @Gst.Since(minor = 18)
    Y444_16LE,
    /**
     * planar 4:2:0 YUV with interleaved UV plane, 16 bits per channel
     */
    @Gst.Since(minor = 18)
    P016_BE,
    /**
     * planar 4:2:0 YUV with interleaved UV plane, 16 bits per channel
     */
    @Gst.Since(minor = 18)
    P016_LE;

    @Override
    public int intValue() {
        return ordinal();
    }
}
