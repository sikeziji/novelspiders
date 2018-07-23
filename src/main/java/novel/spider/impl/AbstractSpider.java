package novel.spider.impl;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * FileName: AbstractSpider
 * Author:   Wangj
 * Date:     2018/6/21 14:20
 */
public abstract class AbstractSpider {


    /**
     *  2018/6/21
     * 抓取指定小说网站的内容
     * @param url
     * @return
     * @throws Exception
     */
    protected String crawl(String url) throws Exception {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url))) {
            String result = EntityUtils.toString(httpResponse.getEntity(), NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}