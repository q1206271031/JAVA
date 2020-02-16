import lombok.Data;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2020-02-10
 * Time: 10:21
 */
@Data
class Person {
    private String name;
    private String sex;
}
class Singleton {
    private volatile static Singleton uniqueInstance;
    private Singleton() {

    }
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class){
                if(uniqueInstance == null){
                    //进入区域后，再检查一次，如果仍是null,才创建实例
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}

/**
 * 静态内部类实现单利模式
 */
class Singleton2 {
    private Singleton2() {

    }
    /** 对外提供公共的访问方法 */
    public static Singleton2 getInstance() {
        return UserSingletonHolder.INSTANCE;
    }

    private static class UserSingletonHolder {
        public static final Singleton2 INSTANCE = new Singleton2();
    }

}

/**
 * 枚举实现单利模式
 */
enum  Singleton3 {
    INSTANCE;
    public static Singleton3 getInstance(){
        return INSTANCE;
    }
}
public class Main {

    public static void main(String[] args) {
        Singleton3 singleton1 = Singleton3.getInstance();
        Singleton3 singleton2 = Singleton3.getInstance();
        System.out.println(singleton1 == singleton2);

    }

    public static void main3(String[] args) {
        Singleton2 singleton1 = Singleton2.getInstance();
        Singleton2 singleton2 = Singleton2.getInstance();
        System.out.println(singleton1 == singleton2);

    }

    public static void main2(String[] args) {
        Singleton singleton1 =  Singleton.getInstance();
        Singleton singleton2 =  Singleton.getInstance();
        System.out.println(singleton1==singleton2);
    }


    public static final int RED = 1;
        public static final int GREEN = 2;
        public static final int BLACK = 3;

        public static void main1(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            switch (a) {
                case RED :
                    System.out.println("red");
                    break;
            }
    }
}
