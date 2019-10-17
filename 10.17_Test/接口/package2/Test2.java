package package2;

import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        Student[] students = new Student[]{
                new Student("张三",95),
                new Student("李四",92),
                new Student("王五",96),
                new Student("赵六",97),
        };
        //Arrays.sort(students);
        sort(students);
        System.out.println(Arrays.toString(students));
    }
    public static void sort(Comparable[] array){
        for(int bound = 0;bound < array.length;bound++){
            for(int cur = array.length - 1;cur > bound;cur--){
                if(array[cur - 1].compareTo(array[cur]) > 0){
                    //交换位置
                    Comparable tmp = array[cur - 1];
                    array[cur - 1] = array[cur];
                    array[cur] = tmp;
                }
            }
        }
    }
}
