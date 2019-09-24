public class homework1 {
    public static void main(String[] args) {
        SeqList sq = new SeqList();
        sq.display();
        for(int i = 0;i < 10;i++){
            sq.add(i,i);
        }
        System.out.println("最大值10内插入数据");
        sq.display();
        System.out.println();
        for(int i = 10;i < 20;i++){
            sq.add(i,i);
        }
        System.out.println("扩容");
        sq.display();
        System.out.println();
        System.out.println("插入数据");
        sq.add(5,8);
        sq.display();
        System.out.println("得到顺序表长度" + sq.getSize());
        System.out.println();
        System.out.println("查找是否包含元素：" + sq.contain(8));
        System.out.println("查找元素下标为：" + sq.search(8));
        System.out.println();
        System.out.println("查找元素下标对应的值" + sq.getIndex(5));
        System.out.println();
        System.out.println("修改目标元素下标的值");
        sq.setIndex(5,15);
        sq.display();
        System.out.println();
        sq.remove(8);
        sq.display();
        System.out.println();
        System.out.println("得到顺序表长度" + sq.getSize());
        sq.clear();
        sq.display();


    }
}
