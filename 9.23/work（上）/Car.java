public class Car {
    private String color;
    private String brand;
    //无参
    public Car(){
        this.color = "黑色";
        this.brand = "布加迪";
    }
    public Car(String co,String br){
        this.color = co;
        this.brand = br;
    }
    public String getColor() {
        return color;
    }
    public String getBrand() {
        return brand;
    }
}
