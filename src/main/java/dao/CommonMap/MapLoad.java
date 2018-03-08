package dao.CommonMap;

/**
 * @Created by  qiao
 * @date 18-3-6 下午3:01
 */

public class MapLoad {
    private int user_id;
    private int id;
    private String type;
    private String map_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMap_id() {
        return map_id;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "MapLoad{" +
                "user_id=" + user_id +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", map_id='" + map_id + '\'' +
                '}';
    }
}
