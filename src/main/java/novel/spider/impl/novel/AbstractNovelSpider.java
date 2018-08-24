package novel.spider.impl.novel;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import novel.spider.Enum.NovelSiteEnum;
import novel.spider.entitys.Novel;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Map;

/**
 * FileName: AbstractNovelSpider
 * Author:   Wangj
 * Date:     2018/8/16 16:21
 */
public abstract class AbstractNovelSpider extends AbstractSpider implements INovelSpider {

    /**
     * 默认的抓取方法，最多尝试{@link INovelSpider#MAX_TRY_TIMES}次下载
     * @param url 地址
     * @return
     * @throws Exception
     */
    protected Elements getsTr(String url) throws Exception{
        return getsTr(url,INovelSpider.MAX_TRY_TIMES);
    }

    /**
     *以指定次数的形式解析对应网站
     * @param url 地址
     * @param maxTryTimes 如果为null 则默认为{@link INovelSpider#MAX_TRY_TIMES}
     * @return
     * @throws Exception
     */
    protected Elements getsTr(String url, Integer maxTryTimes)throws Exception {
        maxTryTimes = maxTryTimes ==  null ? INovelSpider.MAX_TRY_TIMES : maxTryTimes;
        Elements trs = null;
       try {
           String result = super.crawl(url);
           Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
           String novelSelector = context.get("novel-selector");
           if (novelSelector == null)
               throw new RuntimeException(NovelSiteEnum.getEnumByUrl(url).getUrl() + ",url=" + url + "目前不支持抓取小说列表");
           Document doc = Jsoup.parse(result);
           doc.setBaseUri(url);
           trs = doc.select(novelSelector);
           return trs;
       }catch (Exception e){

       }
        throw new RuntimeException(url + ",尝试了" + maxTryTimes + "次依然下载失败了！");
    };


}