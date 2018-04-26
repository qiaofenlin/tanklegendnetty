package dao;

import dao.CommonMap.CommonMap;
import dao.CommonMap.MapLoad;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by  qiao
 * @date 18-3-3 下午7:52
 */

public class UserMapInfo  extends CommonMap{

    private int user_id;
    private String username;
    private String session;
    private List mapLoads =new ArrayList(26);


    public UserMapInfo() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List getMapLoads() {
        return mapLoads;
    }

    public void setMapLoads(List mapLoads) {
        this.mapLoads = mapLoads;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "UserMapInfo{" +
                "user_id=" + user_id +
                ", mapLoads=" + mapLoads +
                '}';
    }

}
