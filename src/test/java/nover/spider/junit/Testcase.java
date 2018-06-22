package nover.spider.junit;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.entitys.Chapter;
import novel.spider.impl.BxwxChapterSpider;
import novel.spider.impl.DefaultChapterDetailSpider;
import novel.spider.impl.DefaultChapterSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderUtil;
import org.junit.Test;

import java.util.List;

public class Testcase {
    /**
     * 测试拿到顶点小说的章节列表
     */
    @Test
    public void testGetsChapter() throws Exception {
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter> chapters = spider.getsChapter("http://www.23us.so/files/article/html/1/1969/index.html");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }
    /**
     * 测试拿到顶点文学网的爬取方式
     */
    @Test
    public void testGetSiteContext() {
        System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("https://www.booktxt.net/2_2096/")));
    }


    /**
     * 测试拿到笔下文学的章节列表
     */
    @Test
    public void testGetsChapter3() {
        IChapterSpider spider = new BxwxChapterSpider();
        List<Chapter> chapters = spider.getsChapter("https://www.bxwx9.org/b/5/5740/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }

    /**
     * 爬取顶点小说的完美世界章节内容
     */
    @Test
    public void testGetChapterDetail() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("http://www.23us.so/files/article/html/19/19198/10917119.html"));
    }

    /**
     * 爬取笔下文学的元尊章节内容
     */
    @Test
    public void testGetChapterDetail2() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("https://www.bxwx9.org/b/5/5740/41249369.html").getContent());
    }

}
