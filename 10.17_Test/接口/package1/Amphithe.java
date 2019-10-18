package package1;
//接口的继承类
class Amphithe extends Animal implements IAmphithe{
    public Amphithe(String name){
        super(name);
    }
    @Override
    public void run(){
        System.out.println(this.name + "正在跑");
    }
    @Override
    public void fly(){
        System.out.println(this.name + "正在飞");
    }
}