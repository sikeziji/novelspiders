package novel.spider.impl.chapter;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.entitys.ChapterDetail;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;

/**
 * FileName: AbstactChapterDetailSpider
 * Author:   Wangj
 * Date:     2018/6/21 14:18
 */
public class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {

    private static final String TAG = "AbstactChapterDetailSpi";

    @Override
    public ChapterDetail getChapterDetail(String url) {
        try {
            String result = super.crawl(url);
//            result = result.replace("&nbsp;", "  ").replace("<br />", "\n\t").replace("<br/>", "\n\t");
//            result = result.replace("&nbsp;", "  ").replace("<br />", "\r\n");
//            System.out.println("1" + result);

            String result1 = result.replaceAll("<br />", "/n");

//            System.out.println("2" + result1);

            Document document = Jsoup.parse(result1);
            document.setBaseUri(url);
            Map<String, String> contexts = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));


            //获取章节标题
            String titleSelector = contexts.get("chapter-detail-title-selector");
            String[] splits = titleSelector.split("\\,");
            splits = parseSelector(splits);
            ChapterDetail detail = new ChapterDetail();
            detail.setTitle(document.select(splits[0]).get(Integer.parseInt(splits[1])).text());

            //获取章节内容
            String contentSelector = contexts.get("chapter-detail-content-selector");
            Element first = document.select(contentSelector).first();
            String textContent1 = first.text()
                    .replace("/p","")
                    .replace("/n", "\n")
//                    .replaceAll("\\s[1,5]*","\n\t");
                    ;
//            System.out.println("txt = " + textContent1);

            detail.setContent(textContent1);

//            PrintWriter out = new PrintWriter(new File("E:/Downloads/小说1/" + detail.getTitle() + ".txt"), "UTF-8");
//            out.println(textContent1);
//            out.close();

            //获取前一章地址
            String prevSelector = contexts.get("chapter-detail-prev-selector");
            splits = prevSelector.split("\\,");
            splits = parseSelector(splits);
            detail.setPrev(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
            //获取后一章地址
            String nextSelector = contexts.get("chapter-detail-next-selector");
            splits = nextSelector.split("\\,");
            splits = parseSelector(splits);
            detail.setNext(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            return detail;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 处理集体元素的下标索引
     *
     * @param splits
     * @return
     */
    private String[] parseSelector(String[] splits) {
        String[] newSplits = new String[2];
        if (splits.length == 1) {
            newSplits[0] = splits[0];
            newSplits[1] = "0";
            return newSplits;
        } else {
            return splits;
        }
    }

}