import java.util.Scanner;
public class homework3{
    public static void main(String[] args) {
        String key = "123456";
        int sum = 0;
        Scanner in = new Scanner(System.in);
        while(sum < 3){
            System.out.println("输入密码");
            String words = in.nextLine();
            sum++;
            if(!key.equals(words)){
                System.out.println("密码输入错误，请重新输入！剩余：" + (3-sum) + "次");
                if(sum == 3){
                    System.out.println("次数用尽，将删库跑路");
                    System.exit(0);
                }
            }else{
                break;
            }
        }
        System.out.println("密码输入正确，成功登录");
        in.close();
    }
}