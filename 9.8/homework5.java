public class homework5{
    public static void main(String[] args) {
        mul();
    }

public static void mul(){
    for(int i = 1;i <= 9;i++){
        for(int j = 1;j <= i;j++){
            System.out.print(i + "*" + j + "=" + i*j + " ");
        }
        System.out.println();
    }
}

}