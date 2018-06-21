package nover.spider.junit;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.entitys.Chapter;
import novel.spider.impl.DefaultChapterSpiderSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderUtil;
import org.junit.Test;

import java.util.List;

public class Testcase {
    @Test
    public void testGetsChapter() throws Exception{
        IChapterSpider spider  = new DefaultChapterSpiderSpider();
        List<Chapter> chapters  = spider.getsChapter("http://www.23us.so/files/article/html/1/1969/index.html");
        for (Chapter chapter : chapters){
            System.out.println(chapter);
        }
    }
    @Test
    public void testGetSiteContext() {
        System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("https://www.booktxt.net/2_2096/")));
    }
}
