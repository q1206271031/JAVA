public class homework3{
    public static void main(String[] args) {
        int num = print(4);
        System.out.println(num);
    }
    public static int print(int n){
        if(n < 1){
            return -1;
        }else if(n == 1 || n == 2){
            return n;
        }else{
            return print(n-1) + print(n-2);
        }
    }
}