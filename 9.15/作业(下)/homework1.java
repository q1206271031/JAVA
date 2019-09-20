public class homework1{
    public static void main(String[] args) {
        int num = print(6);
        System.out.println(num);
    }
    public static int print(int n){
        if(n == 1 || n == 2){
            return 1;
        }
        return print(n-1) + print(n-2);
    }
}