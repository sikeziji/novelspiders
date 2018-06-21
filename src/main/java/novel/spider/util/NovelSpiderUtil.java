package novel.spider.util;

import novel.spider.Enum.NovelSiteEnum;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: NovelSpiderUtil
 * Author:   Wangj
 * Date:     2018/6/21 10:32
 */
public class NovelSpiderUtil {
    private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<>();

    static {
        init();
    }


    private NovelSpiderUtil() {
    }

    ;

    @SuppressWarnings("unchecked")
    private static void init() {

        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File("conf/Spider-Rule.xml"));
            Element root = document.getRootElement();
            List<Element> sites = root.elements("site");
            for (Element site : sites) {
                List<Element> subs = site.elements();
                Map<String, String> subMap = new HashMap<>();
                for (Element sub : subs) {
                    String name = sub.getName();
                    String text = sub.getTextTrim();
                    subMap.put(name, text);
                }
                CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * 拿到对应网站的解析规则
     */
    public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum) {
            return CONTEXT_MAP.get(novelSiteEnum);
    }
}