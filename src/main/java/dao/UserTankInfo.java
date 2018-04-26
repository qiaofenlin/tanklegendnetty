package dao;


public class  UserTankInfo {
    private int user_id;
    private String username;
    private String session;
    private int user_tank_info_id;
    private int equpment_turret_id;
    private int equpment_whell_id;
    private int eupment_engin_id;
    private int equpment_armour_id;
    private int HP;
    private int fire;
    private int shotsSpeed;
    private int tankSpeed;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_tank_info_id() {
        return user_tank_info_id;
    }

    public void setUser_tank_info_id(int user_tank_info_id) {
        this.user_tank_info_id = user_tank_info_id;
    }

    public int getEqupment_turret_id() {
        return equpment_turret_id;
    }

    public void setEqupment_turret_id(int equpment_turret_id) {
        this.equpment_turret_id = equpment_turret_id;
    }

    public int getEqupment_whell_id() {
        return equpment_whell_id;
    }

    public void setEqupment_whell_id(int equpment_whell_id) {
        this.equpment_whell_id = equpment_whell_id;
    }

    public int getEupment_engin_id() {
        return eupment_engin_id;
    }

    public void setEupment_engin_id(int eupment_engin_id) {
        this.eupment_engin_id = eupment_engin_id;
    }

    public int getEqupment_armour_id() {
        return equpment_armour_id;
    }

    public void setEqupment_armour_id(int equpment_armour_id) {
        this.equpment_armour_id = equpment_armour_id;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getFire() {
        return fire;
    }

    public void setFire(int fire) {
        this.fire = fire;
    }

    public int getShotsSpeed() {
        return shotsSpeed;
    }

    public void setShotsSpeed(int shotsSpeed) {
        this.shotsSpeed = shotsSpeed;
    }

    public int getTankSpeed() {
        return tankSpeed;
    }

    public void setTankSpeed(int tankSpeed) {
        this.tankSpeed = tankSpeed;
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
        return "UserTankInfo{" +
                "user_id=" + user_id +
                "username=" + username +
                ", user_tank_info_id=" + user_tank_info_id +
                ", equpment_turret_id=" + equpment_turret_id +
                ", equpment_whell_id=" + equpment_whell_id +
                ", eupment_engin_id=" + eupment_engin_id +
                ", equpment_armour_id=" + equpment_armour_id +
                ", HP=" + HP +
                ", fire=" + fire +
                ", shotsSpeed=" + shotsSpeed +
                ", tankSpeed=" + tankSpeed +
                '}';
    }

}
