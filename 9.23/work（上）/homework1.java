import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

public class homework1 {
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        cal.setA(10);
        cal.setB(3);
        System.out.println(cal.getA());
        System.out.println(cal.getB());
        System.out.println("10+3 = " + cal.add(10,3));
        System.out.println("10-3 = " + cal.sub(10,3));
        System.out.println("10*3 = " + cal.mul(10,3));
        System.out.println("10/3 = " + cal.div(10,3));
    }

}

class Calculator{
    private int a;
    private int b;

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
    public int add(int a,int b){
        int x = (a + b);
        return x;
    }
    public int sub(int a,int b){
        int x = (a - b);
        return x;
    }
    public int mul(int a,int b){
        int x = (a * b);
        return x;
    }
    public double div(int a,int b){
        double x = (double)a/b;
        return x;
    }
}
