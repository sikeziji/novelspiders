package nover.spider.junit;

import novel.spider.entitys.Chapter;
import novel.spider.impl.DefaultChapterSpiderSpider;
import novel.spider.interfaces.IChapterSpider;
import org.junit.Test;

import java.util.List;

public class Testcase {
    @Test
    public void test1() throws Exception{
        IChapterSpider spider  = new DefaultChapterSpiderSpider();
        List<Chapter> chapters  = spider.getsChapter("http://www.biquge.tw/0_5");
        for (Chapter chapter : chapters){
            System.out.println(chapter);
        }

    }
}
