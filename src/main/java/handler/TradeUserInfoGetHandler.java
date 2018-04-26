package handler;

import com.alibaba.fastjson.JSONObject;
import dao.JsonKeyword;
import handler.service.TradeUserInfoService;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import utils.FullHttpRequestUtils;
import utils.InterpreterUtils.InterpreterUtil2;
import utils.redis.TankJedisPool;

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
    private TankJedisPool tankJedisPool;

    public TradeUserInfoGetHandler(TankJedisPool tankJedisPool) {
        this.tankJedisPool = tankJedisPool;
    }

    /**
     * @param ctx
     * @param msg
     * 前端 点击  开始游戏 时  从redis中将指定用户的数据取出.
     */
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        String uri= FullHttpRequestUtils.getUri(msg);
        if (uri.equals("TradeUserInfoGet")) {
            lock.lock();
            JSONObject body=FullHttpRequestUtils.ContentToJson(msg);
            boolean StatusFlag=false;
            String username = body.getString(JsonKeyword.USERNAME);
            String sessionid = body.getString(JsonKeyword.SESSION);
            int roomid = body.getInteger(JsonKeyword.ROOMID);

            if(canLogin(username,sessionid)){

                /*在redis中查找并返回结果*/

                StatusFlag =true;



            }else {
                logger.debug( "用户名登录异常.");
                ctx.writeAndFlush("[注意："+username+"]:信息错误，重新登录 ("+StatusFlag+")!!").addListener(ChannelFutureListener.CLOSE);

            }

            /*根据type=init 解析tankcode 生成若干代码块 为一个字符数组 存着多条命令*/
            String[] codes = new String[2];
            codes[0] = "IF HAVETANK {tank.PH}>60  tank.location=(x,x)" +
                        "DO " +
                        "mytank.move (y,y) " +
                        "mytank.shot" +
                        "END";
            /*根据type=start 解析前端扫描到的数据 生成参数 */
            String statusFromView = "" +
                    "{" +
                    "   mytankId:1" +
                    "   mytankLocation:(2,2)" +
                    "   otherTank:null" +
                    "   otherTankLocation:null" +
                    "}";
//            String codeToOpt = codes[0].replace(xxxx);


            /*将参数拼接到tank中 之后调用相应的解析器 生成。 */
            String responseTankStatus ="{" +
                    "   mytankId:1" +
                    "   mytankLocation:(2,2)" +
                    "   otherTank:null" +
                    "   otherTankLocation:null" +
                    "}";
            String codeToOpt =null;
            InterpreterUtil2 interpreterUtil2 = new InterpreterUtil2();
//            interpreterUtil2.opt(codeToOpt);


            ctx.writeAndFlush("[欢迎："+username+"]:开始游戏 ("+StatusFlag+")!!").addListener(ChannelFutureListener.CLOSE);
            lock.unlock();
            logger.info("["+username+"]: 进入房间("+StatusFlag+")!!");
        }else {
            ctx.fireChannelRead(msg);
        }
    }

    private boolean canLogin(String username, String sessionid) {
        return true;
    }

}
