package handler;



import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import dao.JsonKeyword;
import dao.UserPlayInfo;
import dao.UserTankCode;
import handler.service.TradeUserInfoService;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import server.PSServer;
import utils.C3P0Utils;
import utils.FullHttpRequestUtils;
import utils.redis.TankJedisPool;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.ReentrantLock;

public class TankCodeHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(TankCodeHandler.class.getName());
    private TankJedisPool tankJedisPool;
    private ReentrantLock lock = new ReentrantLock();
    private UserTankCode userTankCode =new UserTankCode();
    private UserPlayInfo userPlayInfo;

    public TankCodeHandler(TankJedisPool tankJedisPool) {
        this.tankJedisPool=tankJedisPool;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws JSONException, UnsupportedEncodingException {
        String uri= FullHttpRequestUtils.getUri(msg);
        if (uri.equals("TankCode")) {
            JSONObject body=FullHttpRequestUtils.ContentToJson(msg);
            userTankCode.setUser_id(body.getInteger("user_id"));
            userTankCode.setCode(body.getString("code"));


            System.out.println("-----------------");
            logger.info(userTankCode.toString() + "访问后台返回值===>TankCodeHandler:channelRead");
            System.out.println("-----------------");
            ctx.writeAndFlush(userTankCode.toString()).addListener(ChannelFutureListener.CLOSE);

            String sql = "insert into user_tank_code_info values(null,?,?) ";
            addMapInfo(sql,userTankCode.getUser_id(),userTankCode.getCode());
            lock.lock();
            PSServer.userPlayInfo.setUser_id(userTankCode.getUser_id());
            PSServer.userPlayInfo.setUserTankCode(userTankCode);
            TradeUserInfoService tradeUserInfoService = new TradeUserInfoService(JsonKeyword.TANKCODE);
            tradeUserInfoService.put(userTankCode.getUser_id());
            lock.unlock();
//            System.out.println("/////////////////////userTankCode"+PSServer.userPlayInfo.toString());
           /*查询缓存*/
//            try {
//                jedisClient.hset("CONTENT_LIST",)
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


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

    /*解析tankCodeHandler中的数据,是其装华为可以执行的代码.*/
    /**
     * 写一个简单的解析器
     *  1.分块 把每一个代码块先分出来.
     *  2.分词 将分好的每一个代码块进行分词,根据关键词进行形成可执行的代码.
     *  3.根据 关键词调用相关函数进行处理.
     *
     *  处理函数该怎么划分呢?  可以给玩家提供一个简单的API,让玩家根据API进行带那的编辑
     *
     * 解析器的功能
     *  1.解析tankcode --->也是参数--->一个if对象 并且可以放参数
     *  2.解析前端tank看到的实时信息(参数)---->解析---->放到对应的对象里边--->然后执行.
     *
     *
     *
     *  那么API该提供那些呢?
     *
     *      分为
     *          1.tank map 属性 主要用于做参数 执行具体操作
     *              建立tankOpt 移动    动力提供(真实速度)    可观察范围(得到周围的地图具体信息. 不再观察区的只能显示地面粗略物品,
     *              不显示具体的血量,在观察区时可以看到观察去内障碍和坦克的血量和行驶速度.)
     *          2.控制语句  if while for
     *              定义并指定几个参数的类型. 可以定义数据的个数.
     *
     *      具体解析策略情况.
     *          在安全情况下,坦克的行驶速度,方向及策略
     *          遇到敌方坦克时的措施
     *          遇到自然灾害或者地图中的其他陷阱时如何行动.
     *
     *      while(mytank.HP,map中看到的东西的情况)语句
     *      {
     *          具体执行策略
     *              1.mytank.speed aim
     *                  突然想了一种,在打开游戏时就对坦克的行进路线进行模拟,构建一条最优路径.
     *                  当遇到敌方坦克时,再次规划最有路径.
     *      }
     *
     *
     *      API写法:
     *
     *
     *
     *
     *      具体执行过程:
     *          while(PH>0){
     *              if(){
     *
     *              }else if(){
     *
     *              }else if(){
     *
     *              }else if(){
     *
     *              }else{
     *
     *              }
     *          }
     *
     *      数据给双方传(同步).
     */

}
