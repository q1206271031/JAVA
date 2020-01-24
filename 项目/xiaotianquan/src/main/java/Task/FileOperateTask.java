package Task;

import Task.FileScanCallback;
import app.FileMeta;
import dao.FileOperatorDAO;
import jdk.nashorn.internal.ir.IfNode;
import sun.applet.Main;
import util.DBUtil;
import util.Pinyin4jUtil;

import javax.management.Query;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FileOperateTask implements FileScanCallback {
    @Override
    /**
     * 本地文件夹有数据库文件没有就插入数据库
     * 数据库有而本地文件没有就删除数据库中内容
     */
    public void execute(File dir) {
        //文件比对
        if(dir.isDirectory()){
            //本地文件
            File[] children = dir.listFiles();
            //转换类compose把本地文件转化为list
            List<FileMeta> localMetas = compose(children);
            //数据库文件:jdbc查询实现(根路径查询路径下所有的文件/文件夹)
            List<FileMeta> metas = FileOperatorDAO.query(dir.getPath());
            //遍历本地文件夹
            for (FileMeta localMeta : localMetas){
                //需要FileMeta 实现hashCode()和equals方法
                //如果数据库不包含本地文件
                if(!metas.contains(localMeta)){
                    FileOperatorDAO.insert(localMeta);
                }
            }
            //遍历本地，数据库有，本地没有
            for(FileMeta meta : metas){
                if(!localMetas.contains(meta)){
                    FileOperatorDAO.delete(meta);//如果meta是文件夹，还有删除子文件/子文件夹
                }
            }
        }
    }

    /**
     * File数组转FileMeta.list
     * @param children
     * @return
     */

    private List<FileMeta> compose(File[] children) {
        List<FileMeta> metas = new ArrayList<>();
        if(children != null) {
            for(File child : children){
                //两种方法
//                metas.add(new FileMeta(child.getName(),child.getPath(),
//                        child.length(),child.lastModified(),child.isDirectory()));
                metas.add(new FileMeta(child));
            }
        }
        return metas;
    }



}
