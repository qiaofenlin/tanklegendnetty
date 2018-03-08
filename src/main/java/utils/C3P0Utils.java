package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Created by  qiao
 * @date 18-2-27 上午10:46
 */

public class C3P0Utils {

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int updata(String sql, Object... args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int number = 0;
        connection = dataSource.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
        number = preparedStatement.executeUpdate();

        return number;
    }
}

/*
* public int shortInfo(String sql,String appid,String info,String deviceType){
        int count = 0;
        int a = 0;
            while (true) {
            try {
                a = ConnPoolUtil.updata(sql,appid, info,deviceType);
                return a;
            } catch (Exception e) {
                logger.warn(e + "insertMysql");
                if (count++ >= 2) {
                    logger.error("访问数据库时无法提供服务===>insertMysql:"+a);
                    return a;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    logger.error("insertMysql" + e1);
                }
                continue;
            }
        }
    }
* */
