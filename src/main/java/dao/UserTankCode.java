package dao;

/**
 * @Created by  qiao
 * @date 18-3-8 下午6:32
 */

public class UserTankCode {
    private int id;
    private int user_id;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserTankCode{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", code='" + code + '\'' +
                '}';
    }
}