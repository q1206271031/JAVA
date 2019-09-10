import java.util.Scanner;

public class homework1{
    public static void main(String[] args) {
        System.out.println("输入你的年龄：");
        Scanner in= new Scanner(System.in);
        int age = in.nextInt();
        if(age > 0 && age <= 18){
            System.out.println("少年");
        }else if(age >= 19 && age <= 28){
            System.out.println("青年");
        }else if(age >= 29 && age <= 55){
            System.out.println("中年");
        }else if(age >= 56){
            System.out.println("老年");
        }else{
            System.out.println("瞎搞");
        }
        in.close();
    }
}