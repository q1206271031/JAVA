import java.util.Arrays;

public class SeqList {
    private int[] data = new int[10];
    //有效元素
    private int size = 0;
    //打印输出
    public void display(){
        System.out.println(Arrays.toString(data));
    }
    public void add(int index,int ele){
        if(index < 0 || index > size){
            return;
        }
        //  "="来确保从当前size号码开始添加元素时不会出现数组下标越界问题
        if(this.size >= this.data.length){
            realloc();
        }
        if(index == size){
            this.data[index] = ele;
            this.size++;
            return;
        }
        for(int i = this.size;i > index;i--){
            this.data[i] = this.data[i-1];
        }
        this.data[index] = ele;
        this.size++;
    }
    public void realloc(){
        int[] data2 = new int[this.data.length * 2];
        for(int i= 0;i < this.data.length;i++){
            data2[i] = this.data[i];
        }
        this.data = data2;
    }
    public boolean contain(int find){
        return this.search(find) != -1;
    }
    public int search(int find){
        for(int i = 0;i < this.size;i++){
            if(this.data[i] == find){
                return i;
            }
        }
        return -1;
    }
    public int getIndex(int index){
        return this.data[index];
    }
    public void setIndex(int index,int ele){
        this.data[index] = ele;
    }

    public void remove(int delete){
        int tmp = search(delete);
        if(tmp == -1){
            return;
        }
        //删除最后一个元素
        if(tmp == this.size - 1){
            this.size--;
            return;
        }
        //删除其他元素
        for(int i = tmp;i < size -1;i++){
            this.data[i] = this.data[i+1];
        }
        this.size--;
    }

    public int getSize(){
        return this.size;
    }
    public void clear(){
        this.size = 0;
        this.data = new int[10];
    }


}
