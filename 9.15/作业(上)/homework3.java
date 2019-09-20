public class homework3{
    public static void main(String[] args){
        print(1234);
    }
    public static void print(int n){
        if(n > 9){
            print(n/10);
        }
        System.out.println(n%10);
    }
}