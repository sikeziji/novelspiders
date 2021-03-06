package nover.spider.junit;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.configuration.configuration;
import novel.spider.entitys.Chapter;
import novel.spider.entitys.ChapterDetail;
import novel.spider.entitys.Novel;
import novel.spider.impl.chapter.BxwxChapterSpider;
import novel.spider.impl.chapter.DefaultChapterDetailSpider;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.impl.download.DownloadCallable;
import novel.spider.impl.download.NovelDownload;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderFactory;
import novel.spider.util.NovelSpiderUtil;
import org.junit.Test;

import java.net.URL;
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
     * 测试拿到顶点小说 死亡名单 的章节列表
     */
    @Test
    public void testGetsChapterSWMD() throws Exception {
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter> chapters = spider.getsChapter("http://www.23us.so/files/article/html/2/2469/index.html");
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
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter> chapters = spider.getsChapter("https://www.booktxt.net/2_2096/");
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
     * 爬取顶点小说的完美世界章节内容
     */
    @Test
    public void testGetChapterDetail1() {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("http://www.ddxsku.com/files/article/html/2/2469/1225820.html"));
    }

    /**
     * 爬取笔下文学的元尊章节内容
     */
    @Test
    public void testGetChapterDetail2() throws Exception {
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        String url = "http://www.booktxt.net/2_2096/2197962.html";
        ChapterDetail chapterDetail = spider.getChapterDetail(url);
        INovelDownload download = new NovelDownload();
        configuration config = new configuration();
        config.setPath("/Users/wangjun/Downloads/小说");
        config.setSize(100);
        config.setTryTimes(10);
        new DownloadCallable(config.getPath() + "/" + NovelSiteEnum.getEnumByUrl(url).getUrl() + "/" + "test.txt", chapterDetail, config.getTryTimes()).callForContext();
    }

    @Test
    public void testDownload() {
        String url23us = "http://www.ddxsku.com/files/article/html/2/2469/index.html";
        String urlbook = "https://www.booktxt.net/2_2096/";
        INovelDownload download = new NovelDownload();
        configuration config = new configuration();
        config.setPath("E:/Downloads/小说1");
        config.setSize(100);
        config.setTryTimes(10);
        config.setTitle("死亡名单");
//        download.download("http://www.23us.so/files/article/html/1/1969/", config);
        //url='https://www.booktxt.net/2_2096/2197963.html'

        System.out.println("下载好了，文件保存在：" + download.download(url23us, config) + "这里，赶紧去看看吧！");
    }


    @Test
    public void testSubList() {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ints.add(i * i);
        }
        System.out.println(ints);
        //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        //0-4
        System.out.println(ints.subList(0, ints.size()));
    }

    @Test
    public void testMultiFileMerge() {
        NovelSpiderUtil.multiFileMerge("D:/小说", null, true, "测试");
    }

    @Test
    public void testBxwxGetsNovels() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("http://www.bxwx9.org");
        List<Novel> novels = spider.getsNovel("https://www.bxwx9.org/modules/article/index.php");
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }

    @Test
    public void testDdxsGetsNovels() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("https://www.23us.so/top/allvote_1.html");
        List<Novel> novels = spider.getsNovel("https://www.23us.so/top/allvote_1.html");
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }

    @Test
    public void test36Ker() {

    }

}
