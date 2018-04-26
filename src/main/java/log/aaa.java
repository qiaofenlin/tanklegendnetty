package log;

/**
 * @Created by  qiao
 * @date 18-3-8 下午9:33
 */

public class aaa {
    public int a =1;
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

    public void dis(bbb b){

        System.out.println("11111" + b.toString());
    }

    /**
     * @Created by  qiao
     * @date 18-4-12 下午8:07
     */

    public static class bbb {
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
}
