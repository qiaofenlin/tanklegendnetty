package dao.CommonRepertoryEqupment;

/**
 * @Created by  qiao
 * @date 18-3-4 下午9:16
 */

public class CommonRepertoryEqupmentEngine extends CommonRepertoryEqupment {
    private int equpment_engine_id;
    private String equpment_engine_name;

    public int getEqupment_engine_id() {
        return equpment_engine_id;
    }

    public void setEqupment_engine_id(int equpment_engine_id) {
        this.equpment_engine_id = equpment_engine_id;
    }

    public String getEqupment_engine_name() {
        return equpment_engine_name;
    }

    public void setEqupment_engine_name(String equpment_engine_name) {
        this.equpment_engine_name = equpment_engine_name;
    }

    @Override
    public String toString() {
        return "CommonRepertoryEqupmentEngine{" +
                "equpment_engine_id=" + equpment_engine_id + '\'' +
                ", equpment_engine_name='" + equpment_engine_name + '\'' +
                ", HP=" + super.getHP() + '\'' +
                ", fire=" + super.getFire() + '\'' +
                ", shotspeed=" + super.getShotspeed() + '\'' +
                ", tankspeed=" + super.getTankspeed() + '\'' +
                '}';
    }
}
