package handler;



import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import dao.JsonKeyword;
import dao.UserPlayInfo;
import dao.UserTankCode;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import server.PSServer;
import utils.C3P0Utils;

import java.io.UnsupportedEncodingException;

public class TankCodeHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(TankCodeHandler.class.getName());
    private UserTankCode userTankCode =new UserTankCode();
    private UserPlayInfo userPlayInfo;
    public TankCodeHandler() {
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws JSONException, UnsupportedEncodingException {
        JSONObject body = (JSONObject) msg;
        String type = body.getString(JsonKeyword.TYPE);
        if (type.equalsIgnoreCase(JsonKeyword.TANKCODE)) {
            userTankCode.setUser_id(body.getInteger("user_id"));
            userTankCode.setCode(body.getString("code"));


            System.out.println("-----------------");
            logger.info(userTankCode.toString() + "访问后台返回值===>TankCodeHandler:channelRead");
            System.out.println("-----------------");
            ctx.writeAndFlush(userTankCode.toString()).addListener(ChannelFutureListener.CLOSE);

            String sql = "insert into user_tank_code_info values(null,?,?) ";
            addMapInfo(sql,userTankCode.getUser_id(),userTankCode.getCode());
            PSServer.userPlayInfo.setUserTankCode(userTankCode);
            System.out.println("/////////////////////userTankCode"+PSServer.userPlayInfo.toString());
        } else {
            ctx.fireChannelRead(msg);
        }


    }

    private int addMapInfo(String sql, int user_id, String code){
        int a = 0;
        int count = 0;
        while (true) {
            try {
                a = C3P0Utils.updata(sql, user_id, code);
                logger.info(user_id+"用户,通过mysqlAdd访问数据库返回结果"+a);
                break;
            } catch (Exception e) {
                logger.warn(e +","+user_id+ "===>msqlAdd");
                if (count++ >= 2) {
                    logger.error(user_id+"暂时不能访问数据库===>mysqlAdd "+a);
                    return a;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    logger.error(e1 + "thread sleep is error in mysqlAdd");
                }
                continue;
            }
        }
        return a;
    }
}
