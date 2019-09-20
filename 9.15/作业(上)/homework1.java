public class homework1{
    public static void main(String[] args) {
        int sum = print(5);
        System.out.println(sum);
    }
    public static int print(int n){
        if(n == 1){
            return 1;
        }
        return n * print(n-1);
    }
}