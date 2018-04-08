package handler;


import com.alibaba.fastjson.*;
import com.alibaba.fastjson.serializer.SerializerFeature;
import dao.CommonMap.MapLoad;
import dao.JsonKeyword;
import dao.UserMapInfo;
import handler.service.TradeUserInfoService;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;
import server.PSServer;
import utils.C3P0Utils;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import static dao.JsonKeyword.*;

public class MapHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(MapHandler.class.getName());
    private ReentrantLock lock = new ReentrantLock();
    private UserMapInfo userMapInfo=new UserMapInfo();
    private MapLoad mapLoad =new MapLoad();
    private static List mapInfoArrayList=new ArrayList<>(MAXNUMBMAP);

    public MapHandler() {
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws JSONException, UnsupportedEncodingException {
        JSONObject body = (JSONObject) msg;
        String type = body.getString(JsonKeyword.TYPE);
        if (type.equalsIgnoreCase(JsonKeyword.MAPINFO)) {
            userMapInfo.setUser_id(body.getInteger(JsonKeyword.USERID));
            /*将传进来的msg中mapinfo转换成Array,将这个array转化成一个对象.*/
            JSONArray mapinfoid1 = body.getJSONArray(JsonKeyword.MAPINFOID);
            String js =JSONObject.toJSONString(mapinfoid1, SerializerFeature.WriteClassName);
            mapInfoArrayList =JSONObject.parseArray(js,MapLoad.class);
            for(int i = 0 ; i < mapInfoArrayList.size() ; i++) {
                System.out.println("==========================================");
                System.out.println(mapInfoArrayList.get(i).toString());
            }
            userMapInfo.setMapLoads(mapInfoArrayList);
            logger.info(userMapInfo.toString() + "访问后台返回值===>MapHandler:channelRead");
            ctx.writeAndFlush(userMapInfo.toString()).addListener(ChannelFutureListener.CLOSE);
            addUserMapInfo();
            lock.lock();
            PSServer.userPlayInfo.setUser_id(userMapInfo.getUser_id());
            PSServer.userPlayInfo.setUserMapInfo(userMapInfo);
            TradeUserInfoService tradeUserInfoService = new TradeUserInfoService(JsonKeyword.MAPINFO);
            tradeUserInfoService.put(userMapInfo.getUser_id());
            lock.unlock();
//            PSServer.executor.submit(new Thread(new TradeUserInfoRunnable(PSServer.countDownLatch,JsonKeyword.MAPINFO)));
//            System.out.println("/////////////////////userTankCode"+PSServer.userPlayInfo.toString());
        } else {
            ctx.fireChannelRead(msg);
        }

    }


    public void addUserMapInfo() {
        int rows = 0;
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            String sql=null;
            MapLoad mapLoadone=null;
            for(int i=0;i<mapInfoArrayList.size();i++) {
                try {
                    sql = "insert into user_map_list values(null,?,?,?,?)";
                    /*数组转化为javabean*/
                    String str = mapInfoArrayList.get(i).toString();
                    mapLoadone = JSON.parseObject(str, MapLoad.class);
                    mapLoadone.setUser_id(userMapInfo.getUser_id());
                    System.out.println("---------------+"+mapLoadone.toString());
                    Object[] params = {mapLoadone.getId(),mapLoadone.getUser_id(),  mapLoadone.getType(), mapLoadone.getMap_id()};

                    rows = qr.update(sql, params);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (rows > 0) {
                        logger.info("user_map_list添加成功");
                    } else {
                        System.out.println("添加失败!");
                        logger.warn("user_map_list添加失败");
                    }
                }
            }
    }
}
