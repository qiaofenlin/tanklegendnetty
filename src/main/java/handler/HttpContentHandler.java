package handler;

import com.alibaba.fastjson.JSONObject;
import dao.JsonKeyword;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

public class HttpContentHandler extends ChannelHandlerAdapter {//拆的剩包体
    private static Logger logger = Logger.getLogger(HttpContentHandler.class.getName());

//    private final String uri ;


    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        FullHttpRequest request = (FullHttpRequest)msg;
        System.out.println(request.uri());

        /*获取请求头*/
        HttpHeaders head = request.headers();
        head.get("Cookie");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( head.get("."));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");

        /*获取请求内容*/
        ByteBuf body = request.content();
        byte[] bytes = new byte[body.readableBytes()];
        body.readBytes(bytes);
        String content = new String(bytes,"UTF-8");
        System.out.println("================================1");

        System.out.println(content);
        JSONObject jsonObject = JSON.parseObject(content);
        //-----------------------------
        System.out.println("================================");
//        System.out.println("session"+jsonObject.get(JsonKeyword.SESSION));
        System.out.println("--------------------------------");
        System.out.println(request.content().toString());
        System.out.println("================================");






        ctx.fireChannelRead(jsonObject);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}























