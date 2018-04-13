package utils;

/**
 * @Created by  qiao
 * @date 18-4-12 下午8:07
 */

public class bbb {
    private String a ;
    private String b;

    public bbb(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "bbb{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }

    public void add(){
        String all =this.getB()+"\t" + this.getA();
        System.out.println(all);
    }
}
