public class homework3{
    public static void main(String[] args){
        result();
    }
public static boolean sushu(int num){
    if(num == 1 || num == 0){
        return false;
    }
    for(int i = 2;i < num;i++){
        if(num % i == 0){
            return false;
            }
    }
    return true;
}
public static void result(){
    for(int i = 1;i <= 100;i++){
        if(sushu(i)){
            System.out.println(i);
        }
    }
}
}


