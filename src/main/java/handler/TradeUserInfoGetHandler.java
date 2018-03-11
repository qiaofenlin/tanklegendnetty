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
 * @date 18-3-8 下午8:18
 */

public class TradeUserInfoGetHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(TradeUserInfoGetHandler.class);
    private  ReentrantLock lock = new ReentrantLock();
    private static List keys=new ArrayList<>(6);

    /**
     *
     * @param ctx
     * @param msg
     *
     * 前端 点击  开始游戏 时  从ConcurrentHashMap中将指定用户的数据取出.
     */
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        lock.lock();
        TradeUserInfoService tradeUserInfoService = new TradeUserInfoService();
        JSONObject body = (JSONObject)msg;
            String type = body.getString(JsonKeyword.TYPE);
            if (type.equalsIgnoreCase(JsonKeyword.TREADEUSERINFOGET)) {
                /*获取房间中的用户id*/
                JSONObject trade_userinfo_user_list = body.getJSONObject(JsonKeyword.TREADEUSERINFOUSERLIST);
                int count= trade_userinfo_user_list.getInteger(JsonKeyword.USERCOUNT);
                int[] userlist = new int[6];
                for (int id = 0; id < count; id++){
                    userlist[id] = trade_userinfo_user_list.getInteger(JsonKeyword.USER + id);
                    tradeUserInfoService.get(userlist[id]);
                }
                lock.unlock();
            }else {
                ctx.fireChannelRead(msg);
            }
    }
}
