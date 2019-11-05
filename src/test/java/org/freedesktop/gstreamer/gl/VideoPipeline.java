package org.freedesktop.gstreamer.gl;

import java.util.Objects;

import org.freedesktop.gstreamer.BusSyncHandler;
import org.freedesktop.gstreamer.BusSyncReply;
import org.freedesktop.gstreamer.Caps;
import org.freedesktop.gstreamer.Context;
import org.freedesktop.gstreamer.Element;
import org.freedesktop.gstreamer.ElementFactory;
import org.freedesktop.gstreamer.Pipeline;
import org.freedesktop.gstreamer.gl.GLContext;
import org.freedesktop.gstreamer.gl.GLDisplay;
import org.freedesktop.gstreamer.message.Message;
import org.freedesktop.gstreamer.message.MessageType;
import org.freedesktop.gstreamer.message.NeedContextMessage;

public class VideoPipeline {

    private final GLDisplay glDisplay;

    private final GLContext glContext;

    private final Pipeline pipe;

    public VideoPipeline(Element consumer, GLDisplay glDisplay, GLContext glContext) {
        this.glDisplay = Objects.requireNonNull(glDisplay);
        this.glContext = Objects.requireNonNull(glContext);

        pipe = new Pipeline();

        /*
         * Listeners registered through 'pipe.getBus().connect' are always notified
         * asynchronously, but the NEED_CONTEXT message need to be handled synchronously
         * if we want to answer back by setting an OpenGL context.
         */
        pipe.getBus().setSyncHandler(new BusSyncHandler() {

            @Override
            public BusSyncReply syncMessage(Message message) {
                if (message.getType() == MessageType.NEED_CONTEXT) {
                    onNeedContext((NeedContextMessage) message);
                }
                return BusSyncReply.PASS;
            }
        });

        /**
         * https://github.com/GStreamer/gst-plugins-base/blob/master/gst-libs/gst/gl/gstglmemorypbo.c
         * gstglmemorypbo.c
         * 
         * <pre>
         * static void
         * _upload_pbo_memory (GstGLMemoryPBO * gl_mem, GstMapInfo * info, GstGLBuffer * pbo, GstMapInfo * pbo_info)
         * {
         *   ...
         *   gl->BindBuffer (GL_PIXEL_UNPACK_BUFFER, pbo_id);
         *   gst_gl_memory_texsubimage (GST_GL_MEMORY_CAST (gl_mem), NULL);
         *   gl->Flush (); // +
         *   gl->BindBuffer (GL_PIXEL_UNPACK_BUFFER, 0);
         * }
         * </pre>
         */
        System.out.println(String.join("\n", //
                "┌──────────────────────────────────────────────────────────────────────┐", //
                "│ Note: the corrupted graphic content at the begining of the video is  │", //
                "│       expected with our shared GL context because the 'glupload'     │", //
                "│       element doesn't flush its textures after each render. I don't  │", //
                "│       know (yet) if it is a bug, a known constraint (of being in the │", //
                "│       same thread) or a something else. By the way, the 'nvdec'      │", //
                "│       element doesn't exhibit this problem.                          │", //
                "└──────────────────────────────────────────────────────────────────────┘" //
        ));

        Element videoTestSrc = ElementFactory.make("videotestsrc", "myVideoTestSrc");
        Element capsFilter = ElementFactory.make("capsfilter", "myCapsFilter");
        capsFilter.setCaps(new Caps("video/x-raw,format=RGB"));
        Element glUpload = ElementFactory.make("glupload", "myGlUpload");

        pipe.addMany(videoTestSrc, capsFilter, glUpload, consumer);
        Pipeline.linkMany(videoTestSrc, capsFilter, glUpload, consumer);
    }

    public Pipeline getPipe() {
        return pipe;
    }

    private void onNeedContext(NeedContextMessage message) {
        System.out.println("Need-Context message from " + message.getSource().getName());

        String contextType = message.getContextType();
        switch (contextType) {

        case "gst.gl.GLDisplay":
            Context displayContext = new Context(contextType);
            glDisplay.setToContext(displayContext);
            ((Element) message.getSource()).setContext(displayContext);
            System.out.println("-> Providing display context.");
            break;

        case "gst.gl.app_context":
            Context appContext = new Context(contextType);
            glContext.setToContext(appContext);
            ((Element) message.getSource()).setContext(appContext);
            System.out.println("-> Providing application context (GL context).");
            break;
        }
    }
}
