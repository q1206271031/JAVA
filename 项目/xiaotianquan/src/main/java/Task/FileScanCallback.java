package Task;

import java.io.File;

/*
扫描任务的回调函数
 */
public interface FileScanCallback {

    void execute(File dir);
}
