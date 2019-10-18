import java.util.Arrays;

public class Test_01 {
    public static void main(String[] args) {
        int[] arr = {15,26,29,34,39};
//        bubbleSort_min(arr);
//        System.out.println(Arrays.toString(arr));
//        bubbleSort_max(arr);
//        System.out.println(Arrays.toString(arr));
//
//        System.out.println(ave(arr));
//        reverse(arr);
//        System.out.println(Arrays.toString(arr));
//
//        bubbleSort(arr);
//        System.out.println(Arrays.toString(arr));
        //System.out.println(binarySearch(arr,4));
        //binarySearch_01(arr,29,0,4);
        System.out.println(Max(arr));
    }
    public static int bubbleSort_min(int[] arr){
        for(int i = 0;i < arr.length - 1;i++){
            for(int j = 0;j < arr.length - i - 1;j++){
                 if(arr[j] > arr[j + 1]){
                     int tmp = arr[j];
                     arr[j] = arr[j + 1];
                     arr[j + 1] = tmp;
                 }
            }
        }
        return arr[0];
    }
    public static int bubbleSort_max(int[] arr){
        for(int i = 0;i < arr.length - 1;i++){
            for(int j = 0;j < arr.length - i - 1;j++){
                if(arr[j] < arr[j + 1]){
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr[0];
    }
    public static int ave(int[] arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum / arr.length;
    }
    public static void reverse(int[] arr){
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            int tmp = arr[0];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
    public static int binarySearch(int[] arr,int key){
        int left = 0;
        int right = arr.length-1;
        while(left <= right){
            int mid = (left + right)/2;
            if(key < arr[mid]){
                right = mid - 1;
            }else if(key > arr[mid]){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    //二分查找递归
    private static int binarySearch_01(int[] arr,int key,int left,int right){
        int mid = (left + right) / 2;
        if((key < arr[left]) || (key > arr[right]) || (left > right)){
            return -1;
        }
        if(arr[mid] < key){
            return binarySearch_01(arr,key,mid + 1,right);
        }else if(arr[mid] > key){
            return binarySearch_01(arr,key,left,mid - 1);
        }else{
            return mid;
        }
    }
    public static int Max(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            if(arr[i] <arr[i + 1]){
                max = arr[i + 1];
            }
        }
        return max;
    }
}
