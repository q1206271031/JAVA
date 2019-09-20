// // public class test5{
// //     public static void main(String[] args) {
// //         int[] arr = {9,5,2,7,10,11};
// //         int max = arr[0];
// //         for(int i = 0;i<arr.length;i++){
// //             if(arr[i] > max){
// //                 max = arr[i];
// //             }
// //         }
// //         System.out.println(max);
// //     }
// // }

// public class test5{
//     public static void main(String[] args) {
//         int[] arr = {9,5,2,7,10,11};
//         double ave = avg(arr);
//         System.out.println(ave);
//     }
//     public static double avg(int[] avg){
//         int sum = 0;
//         for(int i = 0;i<avg.length;i++){
//             sum += avg[i];
//         }
//         return (double)sum/avg.length;
//     }
// }


public class test5{
    public static void main(String[] args) {
        int[] arr = {9,5,2,7,10,11};
        int tofind = 11;
        int pos = find(arr,tofind);
        System.out.println("pos = " + pos);
    }
    public static int find(int[] arr,int tofind){
        for(int i = 0;i<arr.length;i++){
            if(tofind == arr[i]){
                return i;
            }
        }
        return -1;
    }
}