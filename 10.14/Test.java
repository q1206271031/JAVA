import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //Age();
        //isPrime(1);
        //printPrime();
        //printYear();
        //printMul();
        //common_Divisor();
        //Sum();
        //Number_9();
        //flower_Number();
        //Log_on();
        //Number_1();
        //partDiv();
        //printEveryNumber(1234);
        guessNumber();
    }

    //第一题
    public static void Age() {
        while (true) {
            System.out.println("请输入要判断的年龄");
            Scanner in = new Scanner(System.in);
            int age = in.nextInt();
            if (age <= 0) {
                System.out.println("输入有误，重新输入");
                continue;
            } else if (age > 0 && age <= 18) {
                System.out.println("少年");
                break;
            } else if (age >= 19 && age <= 28) {
                System.out.println("少年");
                break;
            } else if (age >= 29 && age <= 55) {
                System.out.println("中年");
                break;
            } else {
                System.out.println("老年");
                break;
            }
        }
    }

    //第二题
    public static void isPrime(int num) {
        if (num < 2) {
            System.out.println("不是素数");
        }
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                System.out.println("不是素数");
            }
            System.out.println("是素数");
        }
    }

    //第三题
    public static void printPrime() {
        int num = 1;
        int i = 1;
        for (num = 1; num <= 100; num++) {
            for (i = 2; i < num; i++) {
                if (num % i == 0) {
                    break;
                }
            }
            if (num == i) {
                System.out.println(num);
            }
        }
    }

    //第四题
    public static void printYear() {
        for (int year = 1000; year <= 2000; year++) {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                System.out.println(year);
            }
        }
    }

    //第五题
    public static void printMul() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + "*" + j + "=" + i * j + " ");
            }
            System.out.println();
        }
    }

    //第六题
    public static void common_Divisor() {
        Scanner in = new Scanner(System.in);
        System.out.println("输入两个数字");
        int a = in.nextInt();
        int b = in.nextInt();
        int result = 0;
        for (int i = 1; i <= a; i++) {
            if (a % i == 0 && b % i == 0) {
                result = i;
            }
        }
        in.close();
        System.out.println("最大公约数是：" + result);
    }

    //第七题
    public static void Sum() {
        int i = 1, size = 1;
        double sum = 0, tmp = 0;
        for (i = 1; i < 100; i++) {
            tmp = size * 1.0 / i;
            sum += tmp;
            size *= -1;
        }
        System.out.println("和为：" + sum);
    }
    //第八题
    public static void Number_9() {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if ((i % 10 == 9) || (i / 10 == 9)) {
                sum++;
            }
        }
        System.out.println(sum);
    }
    //第九题
    public static void flower_Number() {
        for (int i = 0; i <= 999; i++) {
            int tmp = i;
            int count = 1;
            int sum = 0;
            while (tmp / 10 != 0) {
                count++;
                tmp /= 10;
            }
            tmp = i;
            while (tmp != 0) {
                sum += Math.pow(tmp % 10, count);
                tmp /= 10;
            }
            if (i == sum) {
                System.out.println(i);
            }
        }
    }
    //第十题
    public static void Log_on(){
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
    //第十一题
    public static void Number_1(){
        int x = 15;
        int sum = 0;
        while(x>0){
            if((x&1) == 1){
                sum++;
            }
            x = x >> 1;
        }
        System.out.println(sum);
    }
    //第十二题
    public static void partDiv(){
        int x = 15;
        int a = 0,b = 0;
        for(int i = 31;i >= 1;i -= 2){
            System.out.print((x >> i) & 1);
        }
        System.out.println();
        for(int i = 30;i >= 0;i -= 2){
            System.out.print((x>>i) & 1);
        }
    }
    //第十三题
    public static void printEveryNumber(int num){
        if(num > 9){
            printEveryNumber(num / 10);
        }
        System.out.println(num % 10);
    }
    //第十四题
    public static void guessNumber(){
        Scanner number = new Scanner(System.in);
        int a = (int) (Math.random()*100+1);
        System.out.println("请输入一个数：1 - 100");
        while(true) {
            int n = number.nextInt();
            if (n == a) {
                System.out.println("有点东西，猜对了");
                break;
            } else if (n < a) {
                System.out.println("猜小了");
            } else {
                System.out.println("猜大了");
            }
        }
    }
}

