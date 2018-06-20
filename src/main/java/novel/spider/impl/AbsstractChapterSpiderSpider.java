package novel.spider.impl;

import novel.spider.entitys.Chapter;
import novel.spider.interfaces.IChapterSpider;
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

public class AbsstractChapterSpiderSpider implements IChapterSpider {
    protected String crawl(String url) throws Exception {
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url))
        ) {
            HttpGet httpGet = new HttpGet(url);

            String result = EntityUtils.toString(httpResponse.getEntity());
            return result;
        }catch (Exception e){
                throw new RuntimeException(e);
        }
    }

    public List<Chapter> getsChapter(String url) {
        try {
            String resutlt = crawl(url);
            Document document = Jsoup.parse(resutlt);
            Elements element = document.select("#list dd a");
            List<Chapter> chapters = new ArrayList<>();
            for (Element a: element) {
                Chapter character = new Chapter();

                character.setTitle(a.text());
                character.setUrl(a.attr("href"));
                chapters.add(character);
                System.out.print(a);
            }
        return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
