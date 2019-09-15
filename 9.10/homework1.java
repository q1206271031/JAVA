public class homework1{
    public static void main(String[] args) {
        int sum = 0;
        for(int i = 1;i <= 100;i++){
            if((i % 10 == 9) || (i / 10 == 9)){
                sum++;
            }
        }
        System.out.println(sum);
    }
}