package com.sonic.thread;

public class ThreadDownload extends Thread{

    private String url;
    private String name;

    public ThreadDownload() {
    }

    public ThreadDownload(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownload wd = new WebDownload();
        wd.download(url, name);
    }

    public static void main(String[] args) {
        ThreadDownload td1 = new ThreadDownload("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578217604818&di=49e8bf3474c9634098bb52a8fe23183f&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1629584214%2C3969931432%26fm%3D214%26gp%3D0.jpg", "新垣结衣.jpg");
        ThreadDownload td2 = new ThreadDownload("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3937586048,4077386646&fm=26&gp=0.jpg","石原里美.jpg");
        ThreadDownload td3 = new ThreadDownload("https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1419452921,3280008496&fm=26&gp=0.jpg", "桥本环奈.jpg");

        // 启动线程（顺序随机）
        td1.start();
        td2.start();
        td3.start();
    }

}
