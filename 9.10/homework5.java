public class homework5{
    public static void main(String[] args) {
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
}