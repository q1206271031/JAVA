package package1;

class Duck extends Animal implements IFlying,IRunning,ISwimming{
    public Duck(String name){
        super(name);
    }
    @Override
    public void fly(){
        System.out.println(this.name + "正在用翅膀飞");
    }
    @Override
    public void run(){
        System.out.println(this.name + "正在用腿跑");
    }
    @Override
    public void swim(){
        System.out.println(this.name + "正在漂在水上");
    }
}
