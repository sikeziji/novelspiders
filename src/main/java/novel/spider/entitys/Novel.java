package novel.spider.entitys;

import javafx.scene.chart.PieChart;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * FileName: Novel
 * Author:   Wangj
 * Date:     2018/8/16 16:25
 */
public class Novel implements Serializable {
    private static final long seriaVersionUID = 4834523404092893662L;
    /**
     * 书名
     */
    private String name;

    /**
     * 作者名
     */
    private String author;
    /**
     * 小说的连接
     */
    private String url;
    /**
     * 小说的类别
     */
    private String type;

    /**
     * 最后一章的章节名
     */
    private String lastUpdateChapter;

    /**
     * 最后一章的连接
     */
    private String lastUpdateChapterUrl;

    /**
     * 最后更新的时间
     */
    private Date lastUpadteTime;

    /**
     * 小说的状态： 1 连载 ， 2 完结
     */
    private int status;

    /**
     * 书名的首字母
     */
    private char firstLetter;

    /**
     * 小说平台的ID
     */
    private int platformId;
    /**
     * 小说存储到我们数据库的时间
     */
    private Data addTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastUpdateChapter() {
        return lastUpdateChapter;
    }

    public void setLastUpdateChapter(String lastUpdateChapter) {
        this.lastUpdateChapter = lastUpdateChapter;
    }

    public String getLastUpdateChapterUrl() {
        return lastUpdateChapterUrl;
    }

    public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
        this.lastUpdateChapterUrl = lastUpdateChapterUrl;
    }

    public Date getLastUpadteTime() {
        return lastUpadteTime;
    }

    public void setLastUpadteTime(Date lastUpadteTime) {
        this.lastUpadteTime = lastUpadteTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public Data getAddTime() {
        return addTime;
    }

    public void setAddTime(Data addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", lastUpdateChapter='" + lastUpdateChapter + '\'' +
                ", lastUpdateChapterUrl='" + lastUpdateChapterUrl + '\'' +
                ", lastUpadteTime=" + lastUpadteTime +
                ", status=" + status +
                ", firstLetter=" + firstLetter +
                ", platformId=" + platformId +
                ", addTime=" + addTime +
                '}';
    }
}