// public class homework4{
//     public static void main(String[] args) {
//         int x = 15;
//         int sum = 0;
//         while(x>0){
//             if((x&1) == 1){
//                 sum++;
//             }
//             x = x >> 1;
//         }
//         System.out.println(sum);
//     }
// }
public class homework4{
    public static void main(String[] args) {
        int x = 15;
        int y = 0;
        int sum = 0;
        while(x>0){
            y = x % 2;
            x /= 2;
            if(y == 1){
                sum++;
            }
        }
        System.out.println(sum);
    }
}