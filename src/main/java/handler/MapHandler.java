package handler;


import com.alibaba.fastjson.*;
import com.alibaba.fastjson.serializer.SerializerFeature;
import dao.CommonMap.MapLoad;
import dao.JsonKeyword;
import dao.UserMapInfo;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;
import utils.C3P0Utils;


import java.io.UnsupportedEncodingException;
import java.util.*;

import static dao.JsonKeyword.*;

public class MapHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(TankHandler.class.getName());

    UserMapInfo userMapInfo=new UserMapInfo();
    MapLoad mapLoad =new MapLoad();
    private static List mapInfoArrayList=new ArrayList<>(MAXNUMBMAP);


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
                System.out.println(mapInfoArrayList.get(i));
            }
            userMapInfo.setMapLoads(mapInfoArrayList);

            System.out.println("-----------------------------");
            System.out.println(userMapInfo.toString());
            System.out.println("-----------------------------");
            /*数组转化为javabean*/
            String str =mapInfoArrayList.get(1).toString();
            MapLoad xxxx = JSON.parseObject(str, MapLoad.class);
            System.out.println(xxxx.toString()+"------");
            System.out.println("-----------------------------");
//            Iterator i = userMapInfo.getMapLoads().iterator();
//            while (i.hasNext()) {
//                System.out.println(i.next());
//            }
//            MapLoad mapLoads1=JSONObject.parseObject(mapInfoArrayList.get(0),new TypeReference<MapLoad>());


            logger.info(userMapInfo.toString() + "访问后台返回值===>MapHandler:channelRead");
            ctx.writeAndFlush(userMapInfo.toString()).addListener(ChannelFutureListener.CLOSE);
            addUserMapInfo();
        } else {
            ctx.fireChannelRead(msg);
        }

    }


    public void addUserMapInfo() {
        int rows = 0;
//        try {
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            for(int i=0;i<mapInfoArrayList.size();i++) {
                String sql = "insert into user_map_list values(null,?,?,?)";
//                JSONObject jsonObject = (JSONObject)mapInfoArrayList.get(1);
//                System.out.println("/////////////+"+jsonObject.toString());
//                System.out.println(mapInfoArrayList.get(1));


// mapInfoArrayList=userMapInfo.getMapLoads();
//                mapLoad =mapInfoArrayList.get(i);
//                userMapInfo.getMapLoads().get(i).getType(),userMapInfo.getMapLoads().get(i).getMapid()
//                MapLoad mapLoad1 =new MapLoad();
//                mapLoad1 =JSONObject.parseObject(String.valueOf(mapInfoArrayList.get(1)),MapLoad.class);
//                Object json=JSONObject.toJavaObject((JSON) mapInfoArrayList,MapLoad.class);
//                System.out.println("666666666666666666666+"+mapLoad1.toString());
//                System.out.println(userMapInfo.getUser_id()+"\t"+mapInfoArrayList.get(i).getType()+"\t"+mapInfoArrayList.get(i).getMapid());
//                Object[] params = {userMapInfo.getUser_id(),mapLoad.getType(),mapLoad.getMapid()};
//                rows = qr.update(sql, params);
            }
//            String sql = "insert into user_map_list values(null,?,?,?)";
//            Object[] params = {};
//            rows = qr.update(sql, params);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
            if (rows > 0) {
                logger.info("user_tank_info添加成功");
            } else {
                System.out.println("添加失败!");
                logger.warn("user_tank_info添加失败");
            }
//        }

    }




}
