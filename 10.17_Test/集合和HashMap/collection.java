import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

//集合
public class collection {
    public static void main(String[] args) {
        Collection<String> list = new ArrayList<>();
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        Object[] array = list.toArray();
        System.out.println(Arrays.toString(array));
        for(String s:list){
            System.out.println(s);
        }
        System.out.println("------------------------------");
        list.remove("c");
        for(String s:list){
            System.out.println(s);
        }
        list.clear();
        System.out.println("------------------------------");
        System.out.println(list.size());
        System.out.println(list.isEmpty());
    }
}
