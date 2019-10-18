package package1;

class Frog extends Animal implements IRunning,ISwimming{
    public Frog(String name){
        super(name);
    }
    @Override
    public void run(){
        System.out.println(this.name + "正在跳");
    }
    @Override
    public void swim(){
        System.out.println(this.name + "正在游泳");
    }
}