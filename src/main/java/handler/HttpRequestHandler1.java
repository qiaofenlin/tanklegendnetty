package handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.JsonKeyword;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Created by  qiao
 * @date 18-4-7 下午5:38
 */

public class HttpRequestHandler1 extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(HttpRequestHandler1.class.getName());
//    private final String URI ;
    public HttpRequestHandler1() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request =(FullHttpRequest)msg;
        String requestUri =request.uri();
        String value = requestUri.substring(1).trim()+"Service";

        ByteBuf body = request.content();
        byte[] bytes = new byte[body.readableBytes()];
        body.readBytes(bytes);
        String content = new String(bytes,"UTF-8");
        logger.info("------------ Print content start------------");
//        System.out.println("==============content");
        System.out.println(content);
        logger.info("------------ Print content end  ------------");
//        System.out.println("==============content");
        JSONObject jsonObject = JSON.parseObject(content);

        String path ="handler."+value;
        Class clazz = Class.forName(path);
        logger.info("------------进入LoginService------------");
//        System.out.println("进入LoginService");
        Method channelRead = clazz.getDeclaredMethod("channelRead", JSONObject.class);
        Object object=channelRead.invoke(clazz.newInstance(),jsonObject);
        logger.info("------------return from LoginService to HttpRequestHandle1------------");
//        System.out.println("return from LoginService to HttpRequestHandle1!!!");
        ctx.writeAndFlush(object);
    }
}
