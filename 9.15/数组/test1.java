import java.util.Arrays;
// public class test1{
//     public static void main(String[] args) {
//         int[] arr = {1,2,3,4,5,6};
//         toString(arr);
//         System.out.println(toString(arr));
//     }
//     public static String toString(int[] arr){
//         String ret = "[";
//         for(int i = 0;i < arr.length;i++){
//             ret += arr[i];
//             if(i != arr.length - 1){
//                 ret += ",";
//             }
//         }
//         ret += "]";
//         return ret;
//     }
// }

// public class test1{
//     public static void main(String[] args) {
//         int[] arr = {1,2,3,4,5,6};
//         // int[] newArr = Arrays.copyOf(arr,arr.length);
//         // System.out.println(Arrays.toString(newArr));

//         // arr[0] = 10;
//         // System.out.println(Arrays.toString(arr));
//         // System.out.println(Arrays.toString(newArr));
//         int[] ret = copyOf(arr);
//         System.out.println(Arrays.toString(ret));
//     }
//     public static int[] copyOf(int[] arr){
//         int[] ret = new int[arr.length];
//         for(int i = 0;i < arr.length;i++){  
//             ret[i] = arr[i];
//         }
//         return ret;
//     }
// }

// public class test1{
//     public static void main(String[] args) {
//         int[] arr = {1,2,3,4,5,6};
//         System.out.println(max(arr));
//     }
//     public static int max(int[] arr){
//         int max = arr[0];
//         for(int i = 1;i < arr.length;i++){
//             if(arr[i] > max){
//                 max = arr[i];
//             }
//         }
//         return max;
//     }
// }

// public class test1{
//     public static void main(String[] args) {
//         int[] arr = {1,2,3,4,5,6};
//         System.out.println(avg(arr));
//     }
//     public static double avg(int[] arr){
//         int sum = 0;
//         for(int x : arr){
//             sum += x;
//         }
//         return (double)sum/(double)arr.length;
//     }
// }

// public class test1{
//     public static void main(String[] args) {
//         int[] arr = {2,6,3,7,9,1};
//         System.out.println(index(arr,6));
//     }
//     public static int index(int[] arr,int findindex){
//         for(int i = 0;i < arr.length;i++){
//             if(arr[i] == findindex){
//                 return i;
//             }
//         }
//         return -1;
//     }
// }

// public class test1{
//     public static void main(String[] args) {
//         int[] arr = {1,2,3,4,5,6};
//         System.out.println(binarySearch(arr,6));
//     }
//     public static int binarySearch(int[] arr,int find){
//         int left = 0;
//         int right = arr.length-1;
//         while(left <= right){
//             int mid = (left + right)/2;
//             if(find < arr[mid]){
//                 right = mid - 1;
//             }else if(find > arr[mid]){
//                 left = mid + 1;
//             }else{
//                 return mid;
//             }
//         }
//         return -1;
//     }
// }

// public class test1{
//     static int count = 0;
//     public static void main(String[] args) {
        
//         int[] arr = makeBigArray();
//         int ret = binarySearch(arr,9999);
//         System.out.println("ret = " + ret + "count = " + count);
        
//     }
//     public static int[] makeBigArray(){
//         int[] arr = new int[10000];
//         for(int i =0;i < 10000;i++){
//             arr[i] = i;
//         }
//         return arr;
//     }
//     public static int binarySearch(int[] arr,int find){
//         int left = 0;
//         int right = arr.length-1;
//         while(left <= right){
//             count++;
//             int mid = (left + right)/2;
//             if(find < arr[mid]){
//                 right = mid - 1;
//             }else if(find > arr[mid]){
//                 left = mid + 1;
//             }else{
//                 return mid;
//             }
//         }
//         return -1;
//     }
// }

// public class test1{
//     public static void main(String[] args) {
//         int[] arr = {1,2,3,6,8,9};
//         System.out.println(isSorted(arr));
//     }
//     public static boolean isSorted(int[] arr){
//         for(int i = 0;i < arr.length - 1;i++){
//             if(arr[i] > arr[i+1]){
//                 return false;
//             }
//         }
//         return true;
//     }
// }

// public class test1{
//     public static void main(String[] args) {
//         int[] arr = {9,5,2,7};
//         bubbleSort(arr);
//         System.out.println(Arrays.toString(arr));
//     }
//     public static void bubbleSort(int[] arr){
//         for(int bound = 0;bound < arr.length;bound++){
//             for(int cur = arr.length - 1;cur > bound;cur--){
//                 if(arr[cur - 1] > arr[cur]){
//                     int tmp = arr[cur-1];
//                     arr[cur-1] = arr[cur];
//                     arr[cur] = tmp;
//                 }
//             }
//         }
//     }
// }

// public class test1{
//     public static void main(String[] args) {
//         int[] arr = {1,2,3,4,5,6};
//         reverse(arr);
//         System.out.println(Arrays.toString(arr));
//     }
//     public static void reverse(int[] arr){
//         int left = 0;
//         int right = arr.length-1;
//         while(left < right){
//             int tmp = arr[left];
//             arr[left] = arr[right];
//             arr[right] = tmp;
//             left++;
//             right--;
//         }
//     }
// }


public class test1{
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        transform(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void transform(int[] arr){
        int left = 0;
        int right = arr.length-1;
        while(left < right){
            while(left < right && arr[left] % 2 == 0){
                left++;
            }
            while(left < right && arr[right] % 2 != 0){
                right--;
            }
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
        }
    }
}
