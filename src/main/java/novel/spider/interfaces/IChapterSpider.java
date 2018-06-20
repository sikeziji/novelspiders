package novel.spider.interfaces;

import novel.spider.entitys.Chapter;

import java.util.List;

public interface IChapterSpider {

    public List<Chapter> getsChapter(String url);
}
