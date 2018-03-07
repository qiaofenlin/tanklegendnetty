package dao.CommonRepertoryEqupment;

/**
 * @Created by  qiao
 * @date 18-3-4 下午9:11
 */

public class CommonRepertoryEqupment {
    private int HP;
    private int fire;
    private int shotspeed;
    private int tankspeed;

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

    public int getShotspeed() {
        return shotspeed;
    }

    public void setShotspeed(int shotspeed) {
        this.shotspeed = shotspeed;
    }

    public int getTankspeed() {
        return tankspeed;
    }

    public void setTankspeed(int tankspeed) {
        this.tankspeed = tankspeed;
    }

    @Override
    public String toString() {
        return "CommonRepertoryEqupment{" +
                "HP=" + HP +
                ", fire=" + fire +
                ", shotspeed=" + shotspeed +
                ", tankspeed=" + tankspeed +
                '}';
    }
}
