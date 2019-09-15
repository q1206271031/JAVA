import java.util.Scanner;
import java.util.Random;
public class Guessnumber{
    public static void main(String[] args) {
        int rand = new Random().nextInt(99)+1;
        Scanner in = new Scanner(System.in);
        System.out.println("输入一个数字:");
        int num = in.nextInt();
        while(num != rand){
            if(num < rand){
                System.out.println("猜小了");
            }else{
                System.out.println("猜大了");
        
            }
            System.out.println("再猜一次");
            num = in.nextInt();
        }
        System.out.println("猜对了");
    }
}