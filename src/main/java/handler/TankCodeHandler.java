package handler;



import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import dao.JsonKeyword;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;

public class TankCodeHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(TankHandler.class.getName());

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws JSONException, UnsupportedEncodingException {
        JSONObject body = (JSONObject) msg;
        String type = body.getString(JsonKeyword.TYPE);
        if (type.equalsIgnoreCase(JsonKeyword.MAPINFO)) {
//            logger.info(userMapInfo.toString() + "访问后台返回值===>MapHandler:channelRead");
//            ctx.writeAndFlush(userMapInfo.toString()).addListener(ChannelFutureListener.CLOSE);
        } else {
            ctx.fireChannelRead(msg);
        }


    }
}
