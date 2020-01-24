package Task;

import java.io.File;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 数据库的 pinyin 和 pinyin_first 不能有not null
 * 否则报错 Abort due to constraint violation
 */


public class FileScanTask {

    private final ExecutorService pool =
            //new ThreadPoolExecutor(4,10,1,TimeUnit.SECONDS,queue);
            Executors.newFixedThreadPool(4);


    //    private static volatile int COUNT;
    private  final AtomicInteger count = new AtomicInteger();   //默认从0

    private  final CountDownLatch latch = new CountDownLatch(1);

    private FileScanCallback callback;

    public FileScanTask(FileScanCallback callback) {
        this.callback = callback;
    }

    //启动根目录扫描任务
    public void startScan(File root) {

//        synchronized (this) {
//            COUNT++;
//        }
        count.incrementAndGet();

        pool.execute(new Runnable() {
            @Override
            public void run() {
                list(root);
            }
        });
    }


    public void list(File dir) {
        if (!Thread.interrupted()) {
            try {
                callback.execute(dir);
//                System.out.println(dir.getPath());
                if (dir.isDirectory()) {
                    File[] children = dir.listFiles();
                    if (children != null && children.length > 0) {
                        for (File child : children) {
                            //启动子线程进行子文件夹扫描任务
                            if (child.isDirectory()) {
                                //                        synchronized (this) {
                                //                            COUNT++;
                                //                        }
                                count.incrementAndGet();
                                pool.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        list(child);
                                    }
                                });
                            } else {
                                callback.execute(child);
//                                System.out.println(child.getPath());
                            }
                        }
                    }
                }
            } finally {
//            synchronized (this){
//                COUNT--;
//                //所有线程执行完毕就唤醒线程
//                if (COUNT == 0) {
//                    this.notifyAll();
//                }
//            }
                //所有线程执行完毕
                if (count.decrementAndGet() == 0) {
                    //通知
                    latch.countDown();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        FileScanCallback callback = new FileOperateTask();
        FileScanTask task = new FileScanTask(callback);
        task.startScan(new File("F:\\Vip视频"));
        synchronized (task) {
            task.wait();
        }
        System.out.println("执行完毕");
    }

    //等待所有扫描任务执行完毕
    public void waitFinish() throws InterruptedException {
//        try {
//            synchronized (this){
//                this.wait();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        //阻塞等待计数器为0
        try {
            latch.await();
        } finally {
            pool.shutdown();//调用每个线程的interrupt()
//            POOL.shutdownNow();//调用每个线程的stop()
        }

    }
}
