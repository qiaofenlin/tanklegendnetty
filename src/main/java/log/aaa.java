package log;

/**
 * @Created by  qiao
 * @date 18-3-8 下午9:33
 */

public class aaa {
    private int a;
    private int b;
    private  String ll ="aaa";
    public aaa() {
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String toString(String aa) {
        return "aaa{" +
                "a=" + a +
                ", b=" + b +
                ", aa= "+ aa +
                '}';
    }


    public boolean equals(String  o) {
        if (o.equals(ll)){
            return true;
        }
        return false;
    }

    public void dis(String aa){

        System.out.println("11111" + aa);
    }
}
