public class test{
    public static void main(String[] args) {
        // int[] arr = {1,2,3,4};
        // System.out.println(arr.length);
        // System.out.println(arr[1]);
        // arr[2] = 100;
        // System.out.println(arr[2]);

        // int[] arr = {1,2,3};
        // System.out.println(arr[3]);

        // int[] arr = {9,5,2,7};
        // for(int i = 0;i < arr.length;i++){
        //     System.out.println(arr[i]);
        // }
        
        //使用for-each遍历
        // for(int x : arr){
        //     System.out.println(x);
        // }
        
        // int[] arr = {1,2,3};
        // print(arr);

        // int num = 0;
        // func(num);
        // System.out.println("num = " + num);

        // int[] arr = {1,2,3};
        // func(arr);
        // System.out.println("arr[0] = " + arr[0]);
        
        //不改变原数组内容创建新数组并*2
        int[] arr = {1,2,3,4};
        int[] out = trans(arr);
        print(out);
    }
    public static void print(int[] arr){
        for(int x : arr){
            System.out.println(x);
        }
    }

    public static int[] trans(int[] arr){
        int[] ret = new int[arr.length]; 
        for(int i = 0;i < arr.length;i++){
            ret[i] = arr[i] * 2;
        }
        return ret;
    }
}