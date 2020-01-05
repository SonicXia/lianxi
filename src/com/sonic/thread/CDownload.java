package com.sonic.thread;

import java.util.concurrent.*;

/**
 * 了解创建线程的方式三：实现 Callable接口 + 重写 call方法
 * call方法可以抛异常，可以返回值
 */
public class CDownload implements Callable<Boolean> {

    private String url; // 远程路径
    private String name; // 储存名字

    public CDownload() {
    }

    public CDownload(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownload wd = new WebDownload();
        wd.download(url, name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CDownload cd1 = new CDownload("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578217604818&di=49e8bf3474c9634098bb52a8fe23183f&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1629584214%2C3969931432%26fm%3D214%26gp%3D0.jpg", "新垣结衣.jpg");
        CDownload cd2 = new CDownload("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3937586048,4077386646&fm=26&gp=0.jpg","石原里美.jpg");
        CDownload cd3 = new CDownload("https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1419452921,3280008496&fm=26&gp=0.jpg", "桥本环奈.jpg");

        // 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        // 提交执行
        Future<Boolean> result1 = ser.submit(cd1);
        Future<Boolean> result2 = ser.submit(cd2);
        Future<Boolean> result3 = ser.submit(cd3);
        // 获取结果
        Boolean r1 = result1.get();
        Boolean r2 = result2.get();
        Boolean r3 = result3.get();
        // 关闭服务
        ser.shutdownNow();



    }


}
