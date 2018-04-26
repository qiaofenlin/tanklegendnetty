package handler;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import dao.JsonKeyword;
import handler.service.TradeUserInfoService;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import server.PSServer;
import utils.FullHttpRequestUtils;
import utils.redis.TankJedisPool;

import java.io.UnsupportedEncodingException;

/**
 * @Created by  qiao
 * @date 18-4-24 下午6:41
 */

public class TankJavaCodeHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(TankJavaCodeHandler.class.getName());
    private TankJedisPool tankJedisPool;

    public TankJavaCodeHandler(TankJedisPool tankJedisPool) {
        this.tankJedisPool=tankJedisPool;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws JSONException, UnsupportedEncodingException {
        String uri= FullHttpRequestUtils.getUri(msg);
        if (uri.equals("TankJavaCode")) {
            JSONObject body=FullHttpRequestUtils.ContentToJson(msg);




            ctx.writeAndFlush(uri).addListener(ChannelFutureListener.CLOSE);



        } else {
            ctx.fireChannelRead(msg);
        }

    }
}
