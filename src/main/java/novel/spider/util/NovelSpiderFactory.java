package novel.spider.util;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.impl.novel.BxwxNovelSpider;
import novel.spider.impl.novel.DdxsNovelSpider;
import novel.spider.interfaces.INovelSpider;

/**
 * FileName: NovelSpiderFactory
 * Author:   Wangj
 * Date:     2018/8/17 10:43
 */
public final class NovelSpiderFactory {
    private NovelSpiderFactory(){
    }
    public static INovelSpider getNovelSpider(String url){
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch (novelSiteEnum){
            case BiXiaWenXue:return  new BxwxNovelSpider();
            case DingDianXiaoShuo: return new DdxsNovelSpider();
            default : throw new RuntimeException(url + "暂时不支持");
        }
        }
    }