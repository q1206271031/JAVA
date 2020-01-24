package util;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 获取数据库连接
 * 1.Class.forName("com.mysql.jdbc.Driver");connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
 * 2.DataSource连接池 可重用  DataSource ds = new MysqlDataSource();
 *  初始化一定数量的数据库连接
 */


//主要用于连接和关闭数据库资源    利用DataSource
public class DBUtil {
    private static volatile DataSource DATA_SOURCE;

    private DBUtil(){

    }
    //获取SQLite数据库本地文件路径（target目录下）
    private static String getUrl() throws URISyntaxException {
        //生成dbName.db 在 target目录下
        String dbName = "xiaotianquan.db";
        //getClassLoader获取类加载器的相对路径
        //而DBUtil.class.getResource("../init.sql")是以class.util.DBUtil为相对路径的
        URL url = DBUtil.class.getClassLoader().getResource(".");
        return "jdbc:sqlite://"+ new  File(url.toURI()).getParent()+File.separator+dbName;
    }

    //获取数据库连接池
    public static DataSource getDataSource() throws URISyntaxException {
        //双重校验锁
        if(DATA_SOURCE==null){
            synchronized (DBUtil.class){
                if(DATA_SOURCE==null){
                    //mysql日期类型：yyyy-MM-dd HH:mm:ss
                    //sqLite日期类型：yyyy-MM-dd HH:mm:ss:SSS(毫秒)
                    SQLiteConfig config = new SQLiteConfig();
                    //从数据库中读取数据类型转化为JAVA的Date类型，然后解析成时间格式
                    config.setDateStringFormat(Util.DATA_PATTERN);
                    DATA_SOURCE = new SQLiteDataSource(config);
                    ((SQLiteDataSource)DATA_SOURCE).setUrl(getUrl());
                }
            }
        }
        return DATA_SOURCE;
    }
    //获取数据库连接
    public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接获取失败");
        }
    }
    //释放数据库资源：反向释放
    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        //只有查询的时候才有ResultSet对象
        try {
            if(resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("释放数据库资源错误");
        }
    }
    //没有查询时的关闭
    public static void close(Connection connection, Statement statement){
        close(connection,statement,null);
    }
    public static void main(String[] args) {
        Connection connection = getConnection();
    }
}
