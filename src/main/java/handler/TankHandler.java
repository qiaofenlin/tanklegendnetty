package handler;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import dao.CommonRepertoryEqupment.CommonRepertoryEqupmentEngine;
import dao.CommonRepertoryEqupment.CommonRepertoryEqupmentTurret;
import dao.CommonRepertoryEqupment.CommonRepertoryEqupmentWhell;
import dao.JsonKeyword;
import dao.UserPlayInfo;
import dao.UserTankInfo;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.log4j.Logger;
import utils.C3P0Utils;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class TankHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(TankHandler.class.getName());
    private  UserPlayInfo userPlayInfo;
    UserTankInfo userTankInfo = new UserTankInfo();

    public TankHandler(UserPlayInfo userPlayInfo) {this.userPlayInfo=userPlayInfo;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws JSONException, UnsupportedEncodingException {
        JSONObject body = (JSONObject) msg;
        String type = body.getString(JsonKeyword.TYPE);
        if (type.equalsIgnoreCase(JsonKeyword.TANKINFO)) {
            userTankInfo.setUser_id(body.getInteger(JsonKeyword.USERID));
            userTankInfo.setUser_tank_info_id(body.getInteger(JsonKeyword.USERTANKINFOID));
            //将接收到的数据存起来.
            recTankInfo(body);
            logger.info(userTankInfo.toString() + "访问后台返回值===>TankHandler:channelRead");
            ctx.writeAndFlush(userTankInfo.toString()).addListener(ChannelFutureListener.CLOSE);
            addUserTankInfo();

        } else {
            ctx.fireChannelRead(msg);
        }

    }


    /*接受数据*/
    public UserTankInfo recTankInfo(JSONObject body) {
        System.out.println("===================123");

        userTankInfo.setEqupment_armour_id(body.getInteger(JsonKeyword.EQUPMENTARMOURID));
        userTankInfo.setEupment_engin_id(body.getInteger(JsonKeyword.EQUPMENTENGINID));
        userTankInfo.setEqupment_turret_id(body.getInteger(JsonKeyword.EQUPMENTTURRETID));
        userTankInfo.setEqupment_whell_id(body.getInteger(JsonKeyword.EQUPMENTWHELLID));
        System.out.println("==========" + "getUserTank()");
        getUserTank();
        logger.info("---------------" + userTankInfo.toString());
        System.out.println("==========" + "getUserTank()");
        return userTankInfo;
    }

    /*调用数据库中的信息.*/
    public UserTankInfo getUserTank() {

        try {
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            String sqlturret = "select * from common_repertory_equpment_turret where equpment_turret_id=?";
            Object[] paramsturret = {userTankInfo.getEqupment_turret_id()};
            CommonRepertoryEqupmentTurret turret = new CommonRepertoryEqupmentTurret();
            turret = qr.query(sqlturret, new BeanHandler<CommonRepertoryEqupmentTurret>(CommonRepertoryEqupmentTurret.class), paramsturret);
            turret.toString();
            logger.info("====================commonRepertoryEqupmentTurret--------------->" + turret.toString());

            String sqlwhell = "select * from common_repertory_equpment_whell where equpment_whell_id=?";
            Object[] paramswhell = {userTankInfo.getEqupment_whell_id()};
            CommonRepertoryEqupmentWhell whell = new CommonRepertoryEqupmentWhell();
            whell = qr.query(sqlwhell, new BeanHandler<CommonRepertoryEqupmentWhell>(CommonRepertoryEqupmentWhell.class), paramswhell);
            whell.toString();
            logger.info("====================CommonRepertoryEqupmentWhell--------------->" + whell.toString());

            String sqlEngine = "select * from common_repertory_equpment_engine where equpment_engine_id=?";
            Object[] paramsEngine = {userTankInfo.getEupment_engin_id()};
            CommonRepertoryEqupmentEngine engine = new CommonRepertoryEqupmentEngine();
            engine = qr.query(sqlEngine, new BeanHandler<CommonRepertoryEqupmentEngine>(CommonRepertoryEqupmentEngine.class), paramsEngine);
            engine.toString();
            logger.info("====================CommonRepertoryEqupmentEngine--------------->" + engine.toString());

            String sqlArmour = "select * from common_repertory_equpment_armour where equpment_armour_id=?";
            Object[] paramsArmour = {userTankInfo.getEqupment_armour_id()};
            CommonRepertoryEqupmentTurret armour = new CommonRepertoryEqupmentTurret();
            armour = qr.query(sqlArmour, new BeanHandler<CommonRepertoryEqupmentTurret>(CommonRepertoryEqupmentTurret.class), paramsArmour);
            armour.toString();
            logger.info("====================commonRepertoryEqupmentArmour--------------->" + armour.toString());

            userTankInfo.setHP(turret.getHP() + whell.getHP() + engine.getHP() + armour.getHP());
            userTankInfo.setFire(turret.getFire() + whell.getFire() + engine.getFire() + armour.getFire());
            userTankInfo.setShotsSpeed(turret.getShotspeed() + whell.getShotspeed() + engine.getShotspeed() + armour.getShotspeed());
            userTankInfo.setTankSpeed(turret.getTankspeed() + whell.getTankspeed() + engine.getTankspeed() + armour.getTankspeed());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userTankInfo;
    }

    /*写入数据库*/
    public void addUserTankInfo(){
        int rows = 0;
        try {
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            String sql = "insert into user_tank_info values(null,?,?,?,?,?,?,?,?,?,?)";
            Object[] params = { userTankInfo.getUser_id(),userTankInfo.getUser_tank_info_id(),userTankInfo.getEqupment_turret_id(),
                    userTankInfo.getEqupment_whell_id(),userTankInfo.getEupment_engin_id(),userTankInfo.getEqupment_armour_id(),userTankInfo.getHP(),
                    userTankInfo.getFire(),userTankInfo.getShotsSpeed(),userTankInfo.getTankSpeed()};
            rows = qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rows > 0) {
                logger.info("user_tank_info添加成功");
            } else {
                System.out.println("添加失败!");
                logger.warn("user_tank_info添加失败");
            }
        }
    }

}
