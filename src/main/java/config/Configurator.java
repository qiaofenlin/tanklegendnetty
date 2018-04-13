package config;

import dao.CommonMap.ConfigKeyword;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Created by  qiao
 * @date 18-2-22 下午2:08
 */

public class Configurator {
    private static Map<String, String> configs;

    private static final String SERVERPATH = "/home/qiao/IdeaProjects/tanklegendnetty/src/main/resources/tanklegendNettyServer.properties";
    private static final String REDISPATH = "/home/qiao/IdeaProjects/tanklegendnetty/src/main/resources/jedis.properties";

    static {
        configs = new ConcurrentHashMap();
    }

    public static void init() {
        BufferedReader serverIn = null;
        BufferedReader redisIn = null;
        String conf = "";

        try {
            serverIn = new BufferedReader(new FileReader(SERVERPATH));
            redisIn = new BufferedReader(new FileReader(REDISPATH));
            while ((conf = serverIn.readLine()) != null) {
                String[] pair = conf.split("=");
                if (pair.length != 2) {
                    throw new Exception("Server config error");
                } else {
                    configs.put(pair[0], pair[1]);
                }
            }
            while ((conf = redisIn.readLine()) != null) {
                String[] pair1 = conf.split("=");
                if (pair1.length != 2) {
                    throw new Exception("Redis config error");
                } else {
                    configs.put(pair1[0], pair1[1]);
                }
            }

            System.out.println("server.host= " + configs.get("server.host") + "\t" + "server.port=" + configs.get("server.port"));
//            System.out.pri;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //连接服务器 获得nettyserver的地址和端口号.
    public static String getServerHost() throws Exception {
        String host = configs.get(ConfigKeyword.SERVER_HOST);
        if (host == null) {
            throw new Exception("Server host Not Found");
        }
        return host;
    }
    public static int getServerPort() throws Exception {
        String str = configs.get(ConfigKeyword.SERVER_PORT);
        int port = 0;
        try {
            if (str != null) {
                port = Integer.parseInt(str);
            } else {
                throw new Exception("Server port Not Found");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Server port format error!");
        }
        return port;
    }



    public static int getJPWorkMinNum() throws Exception {
        String str = configs.get(ConfigKeyword.JP_WORKMINNUM);
        int workMinNum = 0;
        try {
            if (str != null) {
                workMinNum = Integer.parseInt(str);
            } else {
                throw new Exception("WorkMinNum Not Found");
            }
        } catch (NumberFormatException e) {
            throw new Exception("WorkMinNum format error!");
        }
        return workMinNum;
    }

    public static int getJPWorkMaxNum() throws Exception {
        String str = configs.get(ConfigKeyword.JP_WORKMAXNUM);
        int workMaxNum = 0;
        try {
            if (str != null) {
                workMaxNum = Integer.parseInt(str);
            } else {
                throw new Exception("WorkMaxNum Not Found");
            }
        } catch (NumberFormatException e) {
            throw new Exception("WorkMaxNum format error!");
        }
        return workMaxNum;
    }

    public static String getJPHost() throws Exception {
        String host = configs.get(ConfigKeyword.JP_HOST);
        if (host == null) {
            throw new Exception("Jedis pool host Not Found");
        }
        return host;
    }

    public static int getJPPort() throws Exception {
        String str = configs.get(ConfigKeyword.JP_PORT);
        int port = 0;
        try {
            if (str != null) {
                port = Integer.parseInt(str);
            } else {
                throw new Exception("Jedis pool port Not Found");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Jedis pool port format error!");
        }
        return port;
    }
}
