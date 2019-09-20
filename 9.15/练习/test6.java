// public class test6{
//     public static void main(String[] args) {
//         int[] arr = {1,2,3,4};
//         boolean a = isSorted(arr);
//         System.out.println(a);
//     }
//     public static boolean isSorted(int[] arr){
//         for(int i = 0;i<arr.length;i++){
//             if(arr[i]<arr[i+1]){
//                 return true;
//             }
//         }
//         return false;
//     }
// }

public class test6{
    public static void main(String[] args) {
        int[] arr = {9,5,2,7};
        bubbleSort(arr);
        print(arr);
    }

    public static void bubbleSort(int[] arr){
        for(int i = 0;i!= arr.length;i++){
            for(int j = 0;j<arr.length-1;j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }
    public static void print(int[] arr){
        for(int i = 0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}