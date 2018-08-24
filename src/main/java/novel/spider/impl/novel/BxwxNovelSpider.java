package novel.spider.impl.novel;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.entitys.Novel;
import novel.spider.util.NovelSpiderUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 获取笔下文学的数据列表爬取
 * FileName: Bxwx
 * Author:   Wangj
 * Date:     2018/8/16 17:43
 */
public class BxwxNovelSpider extends AbstractNovelSpider {

    public BxwxNovelSpider(){

    }

    @Override
    public List<Novel> getsNovel(String url) {
        List<Novel> novels = new ArrayList<>();

        try {
            Elements trs = super.getsTr(url,2);
            for (int index = 1; index < trs.size(); index++) {
                Element tr = trs.get(index);
                Elements tds = tr.getElementsByTag("td");
                Novel novel = new Novel();
                novel.setName(tds.first().text());
                //TODO 查看下面的是什么意思
                String novelUrl = tds.first().getElementsByTag("a").first().absUrl("href").replace(".htm","/").replace("/binfo/","/b/");
                novel.setUrl(novelUrl);
                novel.setLastUpdateChapter(tds.get(1).text());
                novel.setLastUpdateChapterUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
                novel.setAuthor(tds.get(2).text());
                novel.setLastUpadteTime(NovelSpiderUtil.getDate(tds.get(4).text(),"yy-MM-dd"));
                novel.setStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).text()));
                novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());
                novels.add(novel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
            return novels;
    }
}