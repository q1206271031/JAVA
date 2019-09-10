import java.util.Scanner;
public class homework6{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("输入两个数字");
        int a = in.nextInt();
        int b = in.nextInt();
        int result = 0;
        for(int i = 1;i <= a;i++){
            if(a % i == 0 && b % i == 0){
                result = i;
            }
        }
        in.close();
        System.out.println("最大公约数为：" + result);
    }
}