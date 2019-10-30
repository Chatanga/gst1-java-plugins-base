package org.freedesktop.gstreamer.video;

import static org.freedesktop.gstreamer.glib.Natives.registration;

import java.util.stream.Stream;

import org.freedesktop.gstreamer.glib.NativeObject;
import org.freedesktop.gstreamer.glib.NativeObject.TypeRegistration;

public class Types implements NativeObject.TypeProvider {

    @Override
    public Stream<TypeRegistration<?>> types() {
        return Stream.of(registration(VideoInfo.class, VideoInfo.GTYPE_NAME, VideoInfo::new));
    }

}
