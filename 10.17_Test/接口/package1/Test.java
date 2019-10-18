package package1;

public class Test {
    public static void main(String[] args) {
//        Cat cat = new Cat("圆");
//        walk(cat);
//        Frog frog = new Frog("扁");
//        walk(frog);
//        swim(frog);
//        Duck duck = new Duck("圆");
//        fly(duck);
//        walk(duck);
//        swim(duck);
        Amphithe amphithe = new Amphithe("扁");
        walk(amphithe);
        fly(amphithe);
    }

    public static void fly(IFlying flying){
        System.out.println("去飞");
        flying.fly();
    }

    public static void walk(IRunning running){
        System.out.println("去散步");
        running.run();
    }

    public static void swim(ISwimming swimming){
        System.out.println("去游泳");
        swimming.swim();
    }
}
