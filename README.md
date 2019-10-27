# gst1-java-plugins-base

A Java binding extension for the OpenGL part of the GStreamer [gst-plugins-base](https://gitlab.freedesktop.org/gstreamer/gst-plugins-base) project.
This project relies on [a modified version of the GStreamer core Java binding](https://github.com/Chatanga/gst1-java-core).

## Test it

The test folder contains a GLPlayer example which works with GLX (X11), but should work with other environments too.
You only need to adapt the default value for the `GST_GL_PLATFORM` property in the code.
