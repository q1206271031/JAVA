import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Ttest {
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int A1 = 0,A2 = 0,A3 = 0,A4 = 0,A5 = 0;
        boolean B1 = false,B2 = false,B3 = false,B4 = false,B5 = false;
        int n = 0,flag = -1;
        for(int i = 0;i < N;i++){
            int num = in.nextInt();
            if(num%5 == 0 && num%2 == 0){
                B1 = true;
                A1 += num;
            }else if(num%5 == 1){
                flag *= -1;
                B2 = true;
                A2 += flag*num;
            }else if(num%5 == 2){
                B3 = true;
                A3++;
            }else if(num%5 == 3){
                B4 = true;
                n++;
                A4 += num;
            }else if(num%5 == 4 && num > A5){
                B5 = true;
                A5 = num;
            }
        }
        if(B1){
            System.out.print(A1 + " ");
        }else{
            System.out.print("N ");
        }
        if(B2){
            System.out.print(A2 + " ");
        }else{
            System.out.print("N ");
        }
        if(B3){
            System.out.print(A3 + " ");
        }else{
            System.out.print("N ");
        }
        if(B4){
            System.out.printf("%.1f ",(float)A4/n);
        }else{
            System.out.print("N ");
        }
        if(B5){
            System.out.print(A5 + " ");
        }else{
            System.out.print("N ");
        }
        in.close();
    }

    public static void main2(String[] args) {
        String line = "CREATE TABLE IF NOT EXISTS file_meta (-- 创建文件信息表";
        int index = line.indexOf("--");
        if(index != -1){
            line = line.substring(0,index);
        }
        System.out.println(line);
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            String[] s = new String[n];
            Map<String,Integer> map = new HashMap<>();
            for(int i = 0;i < n;i++){
                String person = in.next();
                map.put(person,0);
                s[i] = person;
            }

            int m = in.nextInt();
            Set<String> set = map.keySet();
            int Invalid = 0;
            for(int i = 0;i< m;i++){
                String tick = in.next();
                if(set.contains(tick)){
                    map.put(tick,map.get(tick) + 1);
                }else{
                    Invalid++;
                }
            }
            for(String k:s){
                System.out.println(k + " : " + map.get(k));
            }
            System.out.println("Invalid : " + Invalid);
        }

        in.close();
    }
}

