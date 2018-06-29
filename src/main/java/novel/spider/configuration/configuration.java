package novel.spider.configuration;

import java.io.Serializable;

/**
 * FileName: configuration
 * Author:   Wangj
 * Date:     2018/6/29 14:25
 */
public class configuration implements Serializable {

    /**
     * 每个线程默认可以下载的最大章节数量
     */
    public static final  int DEFAULT_SIZE = 100;

    /**
     * 下载后的文件保存地址
     */
    private String path;

    /**
     * 每个线程能够下载的最大章节数量
     */
    private int size;

    /**
     * 由于网络或服务器原因下载出错，可重新尝试连接的次数
     */
    private int tryTimes;
    public configuration(){
        this.size = DEFAULT_SIZE;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(int tryTimes) {
        this.tryTimes = tryTimes;
    }
}