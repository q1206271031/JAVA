public class test2{
    public static void main(String[] args) {
        int a = 1729;
        print(a);
    }
    public static void print(int a){
        if(a>9){
            print(a/10);
        }
        System.out.println(a%10);
    }
}