public class homework3 {
    public static void main(String[] args) {
        int[] arr = {0,1,2,3};
        int a = 2;
        int b = 3;
        swap(arr,a,b);
        print(arr);
    }
    public static void swap(int[] arr,int a,int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
//    public void swapNum(int a,int b){
//        this.a = b;
//        this.b = a;
//    }
    public static void print(int[] arr){
        for(int x:arr){
            System.out.println(x);
        }
    }
}
