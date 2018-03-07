package dao.CommonRepertoryEqupment;

import javafx.geometry.HPos;

/**
 * @Created by  qiao
 * @date 18-3-4 下午9:10
 */

public class CommonRepertoryEqupmentTurret extends CommonRepertoryEqupment {
    private int equpment_turret_id;
    private String equpment_turret_name;

    public int getEqupment_turret_id() {
        return equpment_turret_id;
    }

    public void setEqupment_turret_id(int equpment_turret_id) {
        this.equpment_turret_id = equpment_turret_id;
    }

    public String getEqupment_turret_name() {
        return equpment_turret_name;
    }

    public void setEqupment_turret_name(String equpment_turret_name) {
        this.equpment_turret_name = equpment_turret_name;
    }



    @Override
    public String toString() {
        return "CommonRepertoryEqupmentTurret{" +
                "equpment_turret_id=" + equpment_turret_id + '\'' +
                ", equpment_turret_name='" + equpment_turret_name + '\'' +
                ", HP=" + super.getHP() + '\'' +
                ", fire=" + super.getFire() + '\'' +
                ", shotspeed=" + super.getShotspeed() + '\'' +
                ", tankspeed=" + super.getTankspeed() + '\'' +
                '}';
    }

}
