// import java.util.Scanner;
// public class homework6{
//     public static void main(String[] args) {
//         Scanner in = new Scanner(System.in);
//         int count = in.nextInt();
//         out(count);
//         System.out.println(count);
//     }

//     public static int out(int x){
//         if(x < 10){
//             return x;
//         }else{
//             return out(x/10);
//         }
//     }
// }


//输出位二进制
import java.util.Scanner;
public class homework6{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int tmp = 1 << 31;
        for(int i = 0;i < Integer.SIZE-1;i++){
            System.out.print((count & (tmp >>>= 1))>0?1:0);
        }
        System.out.println();
        in.close();
    }
}
