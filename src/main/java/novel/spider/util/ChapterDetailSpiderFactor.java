package novel.spider.util;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.impl.chapter.DefaultChapterDetailSpider;
import novel.spider.interfaces.IChapterDetailSpider;

/**
 * FileName: ChapterDetailSpiderFactor
 * Author:   Wangj
 * Date:     2018/6/29 14:46
 */
public class ChapterDetailSpiderFactor {
    private ChapterDetailSpiderFactor() {
    }

    ;

    /**
     * 通过指定的url拿到实现了IChapterDetailSpider的具体实现类
     *
     * @param url
     * @return
     */
    public static IChapterDetailSpider getChapterDetailSpider(String url) {
        IChapterDetailSpider spider = null;
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch (novelSiteEnum) {
            case DingDianXiaoShuo:
            case BiQuGe:
            case DingDianXiaoShuoWang:
            case BiXiaWenXue:
                spider = new DefaultChapterDetailSpider();
                break;
        }
        return spider;
    }

}
