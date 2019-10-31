package org.freedesktop.gstreamer.video;

import org.junit.Assert;
import org.junit.Test;

public class VideoFormatTest {

    @Test
    public void t() {
        Assert.assertEquals(0, VideoFormat.UNKNOWN.intValue());
        Assert.assertEquals(10, VideoFormat.xBGR.intValue());
        Assert.assertEquals(20, VideoFormat.Y444.intValue());
        Assert.assertEquals(30, VideoFormat.BGR16.intValue());
        Assert.assertEquals(40, VideoFormat.AYUV64.intValue());
        Assert.assertEquals(60, VideoFormat.NV61.intValue());
        Assert.assertEquals(90, VideoFormat.P016_LE.intValue());
    }
}
