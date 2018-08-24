package novel.spider.util;

import novel.spider.Enum.NovelSiteEnum;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    };

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

    /**
     * 多个文件合并成一个文件，合并规则：按照文件名分割排序
     * @param path 基础目录，该根目录下的所有文本文件都会被合并到mergeToFile
     * @param mergeToFile 被合并的文本文件，这个参数可以为null，合并后的文件保存在path/merge.txt
     * @param deleteThisFile 布尔值是否删除该文件
     */
    public static  void multiFileMerge(String path , String mergeToFile,boolean deleteThisFile ,String name )
    {
    mergeToFile = mergeToFile == null ? path +"/merge" +name + ".txt":mergeToFile;
    //文件路径
    File[] files = new File(path).listFiles(new FilenameFilter(){

        @Override
        public boolean accept(File dir, String name) {

            return name.endsWith(".txt");
        }
    });
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
                int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
                if (o1Index > o2Index){
                    return 1;
                }else if (o1Index==o2Index)
                {
                    return 0;
                }else {
                    return -1;
                }
            }
        });
        PrintWriter out = null;
        try {
            out = new PrintWriter(new File(mergeToFile),"UTF-8");
            for (File file:files) {
                BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
                String line = null;
                while ((line = bufr.readLine()) != null)
                {
                    out.println(line);
                }
                bufr.close();

                if (deleteThisFile)
                {
                    file.delete();
                }
            }
        } catch (IOException e) {
            throw  new RuntimeException(e);
        } finally {
            out.close();
        }

    }

    /**
     * 获取书记状态
     */
    public static int getNovelStatus(String status){
        if (status.contains("连载")) {
            return  1;
        }else if (status.contains("完结") || status.contains("完成")){
            return 2;
        }else {
            throw  new RuntimeException("不支持书籍状态:"+status);
        }
    }

    /**
     * 格式化日期字符串为日期对象
     * @param dateStr
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date getDate(String dateStr, String pattern) throws ParseException {
        if ("MM-dd".equals(pattern)) {
            pattern = "yyyy-MM-dd";
            dateStr = getDateField(Calendar.YEAR) + "-" + dateStr;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(dateStr);
        return date;
    }

    /**
     * 获取本时刻的字符量
     * @param field
     * @return
     */
    public static String getDateField(int field) {
        Calendar cal = new GregorianCalendar();
        return cal.get(field) + "";
    }
}