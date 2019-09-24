//实例化汽车对象

public class homework2 {
    public static void main(String[] args) {
        Car c1 = new Car();
        Car c2 = new Car("白色","保时捷");
        System.out.println("无参构造方法");
        System.out.println("颜色为：" + c1.getColor());
        System.out.println("品牌为：" + c1.getBrand());
        System.out.println("有参构造方法");
        System.out.println("颜色为：" + c2.getColor());
        System.out.println("品牌为：" + c2.getBrand());
    }
}

