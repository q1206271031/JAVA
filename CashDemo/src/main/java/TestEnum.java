

/**
 * 1、枚举 其实也是一种类型-》类-》对象-》构造函数-》定义方法
 * 2、RED,BLACK,WHITE 是三个枚举对象
 * 3、我们自定义的枚举，本质上是继承与java.lang.Enum
 * 4、索引是从0开始的
 * 5、面试问题：你知道单利模式吗？
 *     1、那你写一下你的单例模式？
 *     2、学过静态内部类吗？学过-》用静态内部类实现一下单例模式？
 *     3、枚举实现一个单例模式呢？
 *         a:枚举的构造方法，默认是私有的
 *         b:反射-》就算某个类的构造方法是私有的，通过反射还是可以拿到该类的
 *         对象的。但是枚举的话，反射是不可以拿到实例对象的。其实用枚举实现
 *         单例模式是最最安全的。
 * 6、枚举的构造方法，默认是私有的。private
 */



public enum TestEnum {
    //RED,BLACK,WHITE;

    RED("red",0),BLACK("black",10),WHITE("white",4);

    private String color;
    private int key;
    //
    TestEnum(String color,int key) {
        this.color = color;
        this.key = key;
    }

    public static TestEnum getEnumKey (int key) {
        for (TestEnum t: TestEnum.values()) {
            if(t.key == key) {
                return t;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getEnumKey(41));
    }

    public static void main3(String[] args) {
        TestEnum testEnum1 = TestEnum.WHITE;
        TestEnum testEnum2 = TestEnum.RED;

        System.out.println(testEnum1.compareTo(testEnum2));
        System.out.println(WHITE.compareTo(RED));
    }
    public static void main2(String[] args) {
        TestEnum[] testEnums = TestEnum.values();
        for (int i = 0; i < testEnums.length; i++) {
            System.out.print(testEnums[i]+" "+testEnums[i].ordinal()+" ");
        }
        System.out.println();
        //查找枚举对象
        System.out.println(TestEnum.valueOf("BLACK"));
    }
    public static void main1(String[] args) {
        //TestEnum testEnum = TestEnum.BLACK;
        switch (TestEnum.BLACK) {
            case BLACK:
                System.out.println("BLACK");
                break;
            case RED:
                System.out.println("RED");
                break;
            case WHITE:
                System.out.println("RED");
                break;
                default:
                    System.out.println("default");
                    break;

        }
    }
}
