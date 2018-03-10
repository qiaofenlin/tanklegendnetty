package handler;

import dao.JsonKeyword;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import server.PSServer;

import javax.validation.constraints.Null;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @Created by  qiao
 * @date 18-3-8 下午8:18
 */

public class TradeUserInfoHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(TradeUserInfoHandler.class);
    private  ReentrantLock lock = new ReentrantLock();



    public void channelRead(ChannelHandlerContext ctx,Object msg){
        lock.lock();
        JSONObject body = (JSONObject)msg;
        try {
            String type = body.getString(JsonKeyword.TYPE);
            if (type.equalsIgnoreCase(JsonKeyword.TREADEUSERINFO)) {
                if (null != PSServer.userPlayInfo.getUserMapInfo() && null != PSServer.userPlayInfo.getUserTankInfo() && null != PSServer.userPlayInfo.getUserTankCode()){
                    logger.info("tank信息收集完成,可以开始游戏!!");
                    lock.unlock();
                }
                lock.unlock();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
}
