package novel.spider.Enum;

/**
 * FileName: NovelSiteEnum
 * Author:   Wangj
 * Date:     2018/6/21 10:13
 */
public enum NovelSiteEnum {
    DingDianXiaoShuo(1, "23us.so"),
    BiQuGe(2, "biquge.tw"),
    DingDianXiaoShuoWang(3, "booktxt.net"),
    BiXiaWenXue(4, "bxwx9.org"),
    KanShuZhong(5,"kanshuzhong.com");
    private int id;
    private String url;

    NovelSiteEnum(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static NovelSiteEnum getEnumById(int id) {
        switch (id) {
            case 1:
                return DingDianXiaoShuo;
            case 2:
                return BiQuGe;
            case 3:
                return DingDianXiaoShuoWang;
            case 4:
                return BiXiaWenXue;
            case 5:
                return KanShuZhong;
            default:
                throw new RuntimeException("id=" + id + "是不被支持的小说网站");
        }
    }

    public static NovelSiteEnum getEnumByUrl(String url) {
        for (NovelSiteEnum novelSiteEnum : values()) {
                    if (url.contains(novelSiteEnum.url)) {
                        return novelSiteEnum;
            }
        }
        throw new RuntimeException("url=" + url + "是不被支持的小说网站");
    }
}