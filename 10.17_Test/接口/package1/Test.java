package package1;

public class Test {
    public static void main(String[] args) {
        Cat cat = new Cat("圆");
        walk(cat);
    }
    public static void walk(IRunning running){
        System.out.println("去散步");
        running.run();
    }
}
