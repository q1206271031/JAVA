package package1;

class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }
}
//接口隐式抽象
interface IFlying{
    void fly();
}
interface IRunning{
    void run();
}
interface ISwimming{
    void swim();
}