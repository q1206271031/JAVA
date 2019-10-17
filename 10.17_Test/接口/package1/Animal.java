package package1;

class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }
}
interface IFlying{
    void fly();
}
interface IRunning{
    void run();
}
interface ISwimming{
    void swim();
}