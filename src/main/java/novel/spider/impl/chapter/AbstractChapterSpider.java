package novel.spider.impl.chapter;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.entitys.Chapter;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {

    private static final String TAG = "AbstractChapterSpiderSp";

    public List<Chapter> getsChapter(String url) {

        try {
            String resutlt = crawl(url);
            Document document = Jsoup.parse(resutlt);
            document.setBaseUri(url);
            System.out.println(url);
            Elements elements = document.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
            List<Chapter> chapters = new ArrayList<>();
            for (Element a : elements) {
                Chapter character = new Chapter();
                character.setTitle(a.text());
                character.setUrl(a.absUrl("href"));
                chapters.add(character);
            }
            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
