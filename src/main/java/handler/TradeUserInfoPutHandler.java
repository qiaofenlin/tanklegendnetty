package handler;

import com.alibaba.fastjson.JSONObject;
import dao.JsonKeyword;
import handler.service.TradeUserInfoService;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Created by  qiao
 * @date 18-3-11 下午4:20
 */

public class TradeUserInfoPutHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(TradeUserInfoGetHandler.class);
    private ReentrantLock lock = new ReentrantLock();
    private static List keys=new ArrayList<>(6);

    /**
     *
     * @param ctx
     * @param msg
     *
     * 前端 点击    准备   时  将user数据插入到ConcurrentHashMap中.
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        lock.lock();
        TradeUserInfoService tradeUserInfoService = new TradeUserInfoService();
        JSONObject body = (JSONObject)msg;
        String type = body.getString(JsonKeyword.TYPE);
        if (type.equalsIgnoreCase(JsonKeyword.TREADEUSERINFOPUT)) {
                /*获取房间中的用户id*/
            int user_id =body.getInteger(JsonKeyword.USERID);
            tradeUserInfoService.put(user_id);
            lock.unlock();
        }else {
            ctx.fireChannelRead(msg);
        }
    }
}
