package novel.spider.impl.novel;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import novel.spider.Enum.NovelSiteEnum;
import novel.spider.entitys.Novel;
import novel.spider.util.NovelSpiderUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: DdxsNovelSpider
 * Author:   Wangj
 * Date:     2018/10/10 15:41
 */
public class DdxsNovelSpider extends AbstractNovelSpider {


    public DdxsNovelSpider(){

    }


    @Override
    public List<Novel> getsNovel(String url) {

        List<Novel> novels = new ArrayList<>();

        try {
            Elements trs = super.getsTr(url,2);
            for (int index = 1; index < trs.size(); index++) {
                Element tr  = trs.get(index);//遍历Elements中的所有<tr>标签的内容内容
                Elements tds =  tr.getElementsByTag("td");//获取tr下所有td的集合
                Novel novel = new Novel();
                novel.setName(tds.first().text());
                String novelUrl = tds.first().getElementsByTag("a").first().absUrl("href");
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
            e.printStackTrace();
        }

        return novels;
    }
}