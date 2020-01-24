package Task;

import util.DBUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.Statement;

//初始化表操作    创建表
public class DBInit {
    public static void init(){
        try {
            //获取数据库初始化文件的输入流
            InputStream is = DBInit.class.getClassLoader().getResourceAsStream("init.sql");
            //字节流 -> 字符流（给一个转换流）
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            //每一行读取
            while((line = in.readLine()) != null){
                //忽略注释--代码
                int idx = line.indexOf("--");
                if(idx != -1){
                    line = line.substring(0,idx);
                }
                sb.append(line);
            }
            //字符串根据;进行分割
            String[] sqls = sb.toString().split(";");
            Connection connection = null;
            Statement statement  =  null;
            try {
                for (String sql : sqls){
                    //也可以将上面的Statement改为preparedStatement，然后statement=connection.preparedStatement(sql)
                    //然后statement=executedata()
                    connection = DBUtil.getConnection();
                    statement = connection.createStatement();
                    statement.executeUpdate(sql);
                }
            } finally {
                DBUtil.close(connection,statement);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("数据库初始化异常");
        }
    }

    /*
    public static void main(String[] args){
        init();
    }
    */
}
