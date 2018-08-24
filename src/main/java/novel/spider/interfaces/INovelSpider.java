package novel.spider.interfaces;

import novel.spider.entitys.Novel;

import java.util.List;

public interface INovelSpider {

    public static final int MAX_TRY_TIMES = 3;

    public List<Novel> getsNovel(String url);
}
