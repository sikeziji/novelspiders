package novel.spider.entitys;

import java.io.Serializable;

/**
 * 成员属性s
 */
public class Chapter implements Serializable {
    private static final long seriaVersionUID = -7574082095190014403L;
    private String title;
    private String url;

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
