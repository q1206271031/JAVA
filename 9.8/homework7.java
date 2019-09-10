public class homework7{
    public static void main(String[] args) {
        int i = 1,size = 1;
        double sum = 0,tmp = 0;
        for(i = 1;i < 100;i++){
            tmp = size * 1.0 / i;
            sum += tmp;
            size *= -1;
        }
        System.out.println("和为：" + sum);
    }
}