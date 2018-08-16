package novel.spider.util;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.impl.chapter.BxwxChapterSpider;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.interfaces.IChapterSpider;

/**
 * FileName: ChapterSpiderFactory
 * Author:   Wangj
 * Date:     2018/6/29 14:18
 */
public class ChapterSpiderFactory {

    private ChapterSpiderFactory(){}

    /**
     * 通过给定的url，返回一个实现了IChapterSpider接口的实现类
     * @param url
     * @return
     */
    public static IChapterSpider getChapterSpider(String url){
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        IChapterSpider chapterSpider = null;
        switch (novelSiteEnum) {
            case BiXiaWenXue:
                chapterSpider = new BxwxChapterSpider();
                break;
            case DingDianXiaoShuo:
                chapterSpider = new DefaultChapterSpider();
                break;
            case DingDianXiaoShuoWang:
                chapterSpider = new DefaultChapterSpider();
                break;
            case BiQuGe:
                chapterSpider = new DefaultChapterSpider();
                break;
        }
        return chapterSpider;
    }
}