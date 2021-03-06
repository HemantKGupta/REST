package com.hkg.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

public class SimpleEchoServlet  extends WebSocketServlet {

    @Override
    protected StreamInbound createWebSocketInbound(String string, HttpServletRequest hsr) {
        return new SimpleEchoInbound();
    }

    private static final class SimpleEchoInbound extends MessageInbound {

        public SimpleEchoInbound() {
            super();
        }

        @Override
        protected void onBinaryMessage(ByteBuffer message) throws IOException {
        	System.out.println("called binary.");
            getWsOutbound().writeBinaryMessage(message);
        }

        @Override
        protected void onTextMessage(CharBuffer message) throws IOException {
        	System.out.println("called text.");
            getWsOutbound().writeTextMessage(message);
          
        }
    }
}
