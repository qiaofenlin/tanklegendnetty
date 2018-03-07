package handler;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import dao.User;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.log4j.Logger;

import dao.JsonKeyword;
import redis.clients.jedis.exceptions.JedisConnectionException;
import utils.C3P0Utils;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;


public class LoginHandler extends ChannelHandlerAdapter {
    private static Logger logger = Logger.getLogger(LoginHandler.class.getName());
    private ReentrantLock lock = new ReentrantLock();
    /*查看访问了几次*/
    private static int i = 0;
    private static User user;

    public LoginHandler() {
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws JSONException, UnsupportedEncodingException {
        JSONObject body = (JSONObject)msg;
        String type = body.getString(JsonKeyword.TYPE);

        if(type.equalsIgnoreCase(JsonKeyword.LOGIN)){
            String username = body.getString(JsonKeyword.USERNAME);
            String passwd = body.getString(JsonKeyword.PASSWORD);
            String can = "0";
            if(canLogin(username,passwd)){
                can = "1";
            }
//            System.out.println("++++++++++++++++++++++++++++           "+ can);
            logger.info(username+"访问后台返回值===>Loginhandler:channelRead"+can);
            ctx.writeAndFlush(can).addListener(ChannelFutureListener.CLOSE);

            lock.lock();
            System.out.println(body+" :: "+(i++));
            lock.unlock();

        }else {
            ctx.fireChannelRead(msg);
        }
    }

    private boolean canLogin(String username,String passwd){


        System.out.println("==================================canLogin");
        System.out.println(username+"  "+passwd+"  ");
        System.out.println("==================================canLogin");


        int count = 0;

        while (count < 3) {
            try{

                QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
                // 2.编写SQL语句
//                String sql = "insert into tbl_user values(null,?,?)";
                String sql1 = "select * from tbl_user where uname=?";

                // 3.为站位符设置值
//                Object[] params = { "aa", "bb" };
                Object[] params1 = {};

                // 4.执行添加操作
//                int rows = 0;
//                try {
//                    rows = qr.update(sql, params);
//                    if (rows > 0) {
//                        System.out.println("添加成功!");
//                    } else {
//                        System.out.println("添加失败!");
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                //执行查询操作
//                User user = new User();
                try {
                    user = qr.query(sql1, new BeanHandler<User>(User.class),username);
                    System.out.println(user.getUname() + " : " + user.getUpassward());


                    if(user.getUpassward()!=null && user.getUpassward().equals(passwd)){
                        logger.info(username+"===>canLogin true");
                        System.out.println("========================");
                        System.out.println(username+"666  "+passwd+"  ");
                        System.out.println("========================");
                        return true;
                    }
                    logger.info(username+"===>canLogin false");


                } catch (SQLException e) {
                    e.printStackTrace();
                }




//                String passwd0 = jedis.hget(JsonKeyword.USERS,username);
//                if(passwd0 != null && passwd0.equals(passwd)){
//                    sJedisPool.putbackConnection(jedis);
//                    logger.info(username+"===>canLogin true");
//                    return true;
//                }
//                sJedisPool.putbackConnection(jedis);
//                logger.info(username+"===>canLogin false");
                return false;
            }catch(JedisConnectionException e){
//                sJedisPool.repairConnection(jedis);
//                logger.warn(e+"===>login : "+"redis connection down!");
//                count ++;
//                if(count >= 3){
//                    sJedisPool.putbackConnection(jedis);
//                    logger.info(username+"暂时无法访问redis===>canLogin false");
//                    return false;
//                }
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e1) {
//                    logger.error(e1+"thread sleep is error in canLogin");
//                }
            }
        }
        return false;
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();;
        ctx.close();
    }

}
