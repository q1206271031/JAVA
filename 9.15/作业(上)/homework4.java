public class homework4{
    public static void main(String[] args) {
        int sum = print(1234);
        System.out.println(sum);
    }
    public static int print(int n){
        int sum = 0;
        if(n < 10){
            return n;
        }
        return n%10 + print(n/10);
    }
}