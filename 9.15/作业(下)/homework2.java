public class homework2{
    public static void main(String[] args) {
        int layer = 2;
        move(layer,'A','B','C');
    }
    public static void move(int layer,char origin,char mid,char fal){
        if(layer == 1){
            System.out.println("从" + origin + "移动盘子" + layer + "号到" + fal);
        }else{
            move(layer-1,origin,fal,mid);
            System.out.println("从" + origin + "移动盘子" + layer + "号到" + fal);
            move(layer-1,mid,origin,fal);
        
        }
    }
}
