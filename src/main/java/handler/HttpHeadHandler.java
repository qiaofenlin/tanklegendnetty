package handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;

import static io.netty.handler.codec.stomp.StompHeaders.CONTENT_LENGTH;
import static io.netty.handler.codec.stomp.StompHeaders.CONTENT_TYPE;


public class HttpHeadHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(HttpHeadHandler.class.getName());

        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws UnsupportedEncodingException {
        String body = (String)msg;
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set(CONTENT_TYPE,"text/html;charset=UTF-8");
        response.headers().set(CONTENT_LENGTH,body.getBytes("UTF-8").length+"");
        response.content().writeBytes(body.getBytes("UTF-8"));
        ctx.write(response);
    }
}
