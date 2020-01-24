package app;

import Task.DBInit;
import Task.FileScanCallback;
import Task.FileScanTask;
import Task.FileOperateTask;
import dao.FileOperatorDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private GridPane rootPane;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<FileMeta> fileTable;

    @FXML
    private Label srcDirectory; //设置标签
    private Thread t;

    public void initialize(URL location, ResourceBundle resources) {
        DBInit.init();
        // 添加搜索框监听器，内容改变时执行监听事件
        searchField.textProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                freshTable();
            }
        });
    }

    public void choose(Event event) {
        // 选择文件目录
        DirectoryChooser directoryChooser=new DirectoryChooser();
        Window window = rootPane.getScene().getWindow();
        File file = directoryChooser.showDialog(window);

        if(file == null)
            return;
        // 获取选择的目录路径，并显示
        String path = file.getPath();
        // TODO
//        System.out.println(path);
//        srcDirectory.setText(path);
        if (t != null) {
            t.interrupt();
        }
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                //伪代码
//                FileScanTask task = new FileScanTask();
//                task.start();   //多线程文件扫描任务
//                //wait() 等待Task()结束
//                task.waitFinish();


                //当当前线程运行完毕之后才会继续执行被打断的线程
                //FileOperatorDAO实现了回调接口，FileScanTask使用了回调接口
                try {
                    FileScanCallback callback = new FileOperateTask();
                    FileScanTask task = new FileScanTask(callback);
                    task.startScan(file);//多线程完成文件扫描任务
                    task.waitFinish();
                    System.out.println("执行完毕");
                    //当前搜索框
                    freshTable();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    // 刷新表格数据
    private void freshTable(){
        ObservableList<FileMeta> metas = fileTable.getItems();
        metas.clear();
        // TODO
        //List<FileMeta> datas = new ArrayList<>();
//        datas.add(new FileMeta());
//        datas.add(new FileMeta("A","D:/",23883L,new Date().getTime(),true));
//        datas.add(new FileMeta("B","E:/",34483L,new Date().getTime(),true));
//        datas.add(new FileMeta("C","F:/",383L,new Date().getTime(),true));
//        datas.add(new FileMeta("D","G:/",548357883L,new Date().getTime(),true));

        List<FileMeta> datas = FileOperatorDAO.search(srcDirectory.getText(),searchField.getText());
        metas.addAll(datas);

    }
}