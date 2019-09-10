public class homework2{
    public static void main(String[] args){
        int a = 5,b = 3,c = 2;
        int max = a > b ? (a>c?a:c) : (b>c?b:c);
        int min = a < b ? (a<c?a:c) : (b<c?b:c);
        System.out.println("最大值为： " + max);
        System.out.println("最小值为： " + min);

    }
}