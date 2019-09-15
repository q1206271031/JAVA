public class homework2{
    public static void main(String[] args) {
        for(int i = 0;i <= 999;i++){
            int tmp = i;
            int count = 1;
            int sum = 0;
            while(tmp/10 != 0){
                count++;
                tmp /= 10;
            }
            tmp = i;
            while(tmp != 0){
                sum += Math.pow(tmp % 10,count);
                tmp /= 10;
            }
            if(i == sum){
                System.out.println(i);
            }
        }
    }
}