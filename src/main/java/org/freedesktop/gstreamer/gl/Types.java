package org.freedesktop.gstreamer.gl;

import static org.freedesktop.gstreamer.glib.Natives.registration;

import java.util.stream.Stream;

import org.freedesktop.gstreamer.glib.NativeObject;
import org.freedesktop.gstreamer.glib.NativeObject.TypeRegistration;

public class Types implements NativeObject.TypeProvider {

	@Override
	public Stream<TypeRegistration<?>> types() {
		return Stream.of( //
				registration(GLDisplay.class, GLDisplay.GTYPE_NAME, GLDisplay::new),
				registration(GLContext.class, GLContext.GTYPE_NAME, GLContext::new),
				registration(GLWrappedContext.class, GLWrappedContext.GTYPE_NAME, GLWrappedContext::new));
	}

}
