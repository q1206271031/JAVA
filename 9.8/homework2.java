import java.util.Scanner;

public class homework2{
    public static void main(String[] args) {
        System.out.println("输入数字：");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if(num == 1 || num == 0){
            System.out.println("不是素数");
        }
        for(int i = 2;i < num;i++){
            if(num % i == 0){
            System.out.println("不是素数"); 
            break;   
            }
            System.out.println("是素数");
        }
        in.close();
    } 
}

    