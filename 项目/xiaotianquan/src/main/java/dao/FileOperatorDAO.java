package dao;

import app.FileMeta;
import sun.applet.Main;
import util.DBUtil;
import util.Pinyin4jUtil;

import javax.management.Query;
import java.io.File;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperatorDAO {

    public static List<FileMeta> query(String dirPath) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<FileMeta> metas = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select name, path, size, last_modified, is_directory" +
                    " from file_meta where path=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, dirPath);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String path = resultSet.getString("path");
                Long size = resultSet.getLong("size");
                Long last_modified = resultSet.getLong("last_modified");
                Boolean is_directory = resultSet.getBoolean("is_directory");
                FileMeta meta = new FileMeta(name, path, size, last_modified, is_directory);
                metas.add(meta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return metas;
    }


    public static void insert(FileMeta localMeta) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            try {
                //1.获取数据库连接
                connection = DBUtil.getConnection();
                String sql;
                sql = "insert into file_meta" +
                        "(name,path,is_directory,pinyin,pinyin_first,size,last_modified)" +
                        " values (?,?,?,?,?,?,?)";
                //2.获取操作命令对象
                statement = connection.prepareStatement(sql);
                //填充占位符
                statement.setString(1, localMeta.getName());
                statement.setString(2, localMeta.getPath());
                statement.setBoolean(3, localMeta.getDirectory());
                String pinyin = null;
                String pinyin_first = null;
                //包含中文字符时需要保存全拼和拼音首字母
                if (Pinyin4jUtil.containsChinese(localMeta.getName())) {
                    String[] pinyins = Pinyin4jUtil.get(localMeta.getName());
                    pinyin = pinyins[0];
                    pinyin_first = pinyins[1];
                }
                statement.setString(4, pinyin);
                statement.setString(5, pinyin_first);
                statement.setLong(6, localMeta.getSize());
                statement.setTimestamp(7, new Timestamp(localMeta.getLastModified()));

                //3.执行sql语句
                //statement.executeUpdate();
                int n = statement.executeUpdate();  //======================
                System.out.println(statement + "\n" + sql + "\n=============" + n);
            } finally {
                DBUtil.close(connection, statement);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //测试代码
    public static void main(String[] args) {
        System.out.println(query("F:\\比特科技\\3.JavaWeb"));
        //delete(new FileMeta("3.JavaWeb","F:\\比特科技",0L,0L,true));
    }




    public static void delete(FileMeta meta) {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //1.获取数据库连接
            connection = DBUtil.getConnection();
            //默认自动提交，执行一条，先不提交到数据库，最后提交
            connection.setAutoCommit(false);
            String sql;
            sql = "delete from file_meta" +
                    " where name=?" +
                    " and path=? and is_directory=?";
            //2.获取操作命令对象
            statement = connection.prepareStatement(sql);
            //填充占位符
            statement.setString(1, meta.getName());
            statement.setString(2, meta.getPath());
            statement.setBoolean(3, meta.getDirectory());

            //3.执行sql语句
            statement.executeUpdate();
            //删除子文件/子文件夹
            if(meta.getDirectory()){
                sql = "delete from file_meta where path=? or path like ?";
                statement = connection.prepareStatement(sql);
                String path = meta.getPath()+File.separator + meta.getName();
                statement.setString(1,path);
                statement.setString(2,path+File.separator + "%");
//                statement.setString(1,
//                        meta.getPath()+File.separator+meta.getName()+File.separator + "%");
                //多加了一个separator保证保留了文件夹
                statement.executeUpdate();
            }
            connection.commit();


        } catch (Exception e) {
            e.printStackTrace();
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            DBUtil.close(connection, statement);
        }
    }


    public static List<FileMeta> search(String dir, String text) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<FileMeta> metas = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            boolean empty = dir==null || dir.trim().length() == 0;
            String sql = "select name, path, size, last_modified, is_directory" +
                    " from file_meta where (name like ? or pinyin like ?" +
                    " or pinyin_first like ?)"
                    + (empty ?
                    "" : " and (path=? or path like ?)");
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%"+text+"%");
            statement.setString(2, "%"+text+"%");
            statement.setString(3, "%"+text+"%");

            if (!empty) {
                statement.setString(4, dir);
                statement.setString(5, dir+File.separator+"%");
            }
            System.out.println("search path="+dir+","+"text="+text);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String path = resultSet.getString("path");
                Long size = resultSet.getLong("size");
                Long last_modified = resultSet.getLong("last_modified");
                Boolean is_directory = resultSet.getBoolean("is_directory");
                FileMeta meta = new FileMeta(name, path, size, last_modified, is_directory);
                System.out.println("search:" + name + path);
                metas.add(meta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return metas;
    }
}



