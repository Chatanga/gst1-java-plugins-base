package org.freedesktop.gstreamer.gl;

import java.util.Objects;

import org.freedesktop.gstreamer.BusSyncHandler;
import org.freedesktop.gstreamer.BusSyncReply;
import org.freedesktop.gstreamer.Context;
import org.freedesktop.gstreamer.Element;
import org.freedesktop.gstreamer.ElementFactory;
import org.freedesktop.gstreamer.FlowReturn;
import org.freedesktop.gstreamer.Pipeline;
import org.freedesktop.gstreamer.lowlevel.GstAPI.GstCallback;
import org.freedesktop.gstreamer.message.Message;
import org.freedesktop.gstreamer.message.MessageType;
import org.freedesktop.gstreamer.message.NeedContextMessage;

public class VideoPipeline {

    public static interface CLIENT_DRAW {
    }

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

        /*
         * Generate a video displaying a Mandelbrot pattern (see
         * https://gstreamer.freedesktop.org/documentation/opengl/gltestsrc.html?gi-
         * language=c#gltestsrc:pattern)
         */
        Element glTestSrc = ElementFactory.make("gltestsrc", "myVideoTestSrc");
        glTestSrc.set("pattern", "13");

        /*
         * Adding a GL filter (doing nothing) seems to have the nice side effect of
         * flushing the GL state. Wihtout this filter, the textures aren't in sync
         * between the two threads and the first textures displayed contain garbage (the
         * others are simply out of sync I suppose).
         * 
         * In addition, this filter (and other GL elements) emits a 'client-draw' signal
         * in the same thread where the textures are generated, giving us an opportunity
         * to flush the GL state ourself if needed.
         */
        Element glFilterApp = ElementFactory.make("glfilterapp", "myGlFilterApp");
        glFilterApp.connect(CLIENT_DRAW.class, null, new GstCallback() {
            public FlowReturn callback(Element element, int texture, int width, int height) {
                return FlowReturn.OK;
            }
        });

        pipe.addMany(glTestSrc, glFilterApp, consumer);
        Pipeline.linkMany(glTestSrc, glFilterApp, consumer);
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
