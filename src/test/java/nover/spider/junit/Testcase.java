package nover.spider.junit;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.configuration.configuration;
import novel.spider.entitys.Chapter;
import novel.spider.impl.chapter.BxwxChapterSpider;
import novel.spider.impl.chapter.DefaultChapterDetailSpider;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.impl.download.NovelDownload;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.util.NovelSpiderUtil;
import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void testDownload() {
        INovelDownload download = new NovelDownload();
        configuration config = new configuration();
        config.setPath("D:/小说");
        config.setSize(100);
        config.setTryTimes(10);
//        download.download("http://www.23us.so/files/article/html/1/1969/", config);
        System.out.println("下载好了，文件保存在：" + download.download("https://www.23us.so/files/article/html/16/16282/index.html", config) + "这里，赶紧去看看吧！");
    }


    @Test
    public void testSubList() {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 10 ;i++) {
            ints.add(i * i );
        }
        System.out.println(ints);
        //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        //0-4
        System.out.println(ints.subList(0, ints.size()));
    }

    @Test
    public void testMultiFileMerge() {
        NovelSpiderUtil.multiFileMerge("D:/小说", null, true ,"测试");
    }

    @Test
    public void test(){
        int  a, b = 2,c;
        a=b;
        c=b;
        if (a == c){
            System.out.println("等于");
        }else{
            System.out.println("不等于");
        }
    }

}
