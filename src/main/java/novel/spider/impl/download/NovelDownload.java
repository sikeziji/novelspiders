package novel.spider.impl.download;

import novel.spider.Enum.NovelSiteEnum;
import novel.spider.configuration.configuration;
import novel.spider.entitys.Chapter;
import novel.spider.entitys.ChapterDetail;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.util.ChapterDetailSpiderFactor;
import novel.spider.util.ChapterSpiderFactory;
import novel.spider.util.NovelSpiderUtil;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

/**
 *  如何实现多线程下载任意网站的小说
 * 	1.拿到该网站的某本小说的所有章节：章节列表
 * 	2.通过计算，将这些章节分配给指定数量的线程，让他们去解析，然后保存到文本 *  文件中
 * 	3.通知主线程，将这些零散的小文件合并成一个大文件。最后将那些分片的小文件 *  删除掉。
 *
 * FileName: NovelDownload
 * Author:   Wangj
 * Date:     2018/6/29 14:13
 */
public class NovelDownload implements INovelDownload {


    @Override
    public String download(String url, configuration configuration) {
        IChapterSpider spider = ChapterSpiderFactory.getChapterSpider(url);//通过指定URL实现IChapterSpider类方法
        List<Chapter> chapters = spider.getsChapter(url);//返回章节列表
        //某个线程下载完毕后，你要告诉主线程我下载好了
        //所有线程都下载好了，合并
        int size = configuration.getSize();//线程的Size

        //用章节列表的总章数 除 线程的Size 并且用ceil函数向上取整数 : 1222章/100 = 13
        int maxThreadSize = (int) Math.ceil(chapters.size() * 1.0 / size);
        Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<>();


        for (int i = 0; i < maxThreadSize; i++) {
            int formIndex = i * (configuration.getSize());//i = 1 ；formIndex = 200；
            int toIndex = i == maxThreadSize - 1 ? chapters.size() : i * (configuration.getSize()) + configuration.getSize();//如果不是最后一个线程 （i=2)就从 200 - 299章
            downloadTaskAlloc.put(formIndex + "-" + toIndex, chapters.subList(formIndex, toIndex));//章节列表中从formIndex 开始到 toIndex结束
        }
        //线程池 ， 设置线程池中线程的固定数量
        ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
        //将Map中的数据存放如 KeySet中
        Set<String> keySet = downloadTaskAlloc.keySet();
        List<Future<String>> tasks = new ArrayList<>();

        //通过这两段代码就可以创建缺失的路径
        String savePath = configuration.getPath() + "/" + NovelSiteEnum.getEnumByUrl(url).getUrl()+"/";
        new File(savePath).mkdirs();

        for (String key : keySet) {
            tasks.add(service.submit(new DownloadCallable(savePath + "/" + key + ".txt", downloadTaskAlloc.get(key), configuration.getTryTimes())));
        }

        service.shutdown();
        for (Future<String> future : tasks) {
            try {
                System.out.println(future.get() + ",下载完成!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        NovelSpiderUtil.multiFileMerge(savePath, null, true , chapters.get(0).getTitle());
        System.out.println(chapters.get(1).getTitle());
        return savePath + "/merge"+chapters.get(1).getTitle()+".txt";
    }
}

class DownloadCallable implements Callable<String> {
    private List<Chapter> chapters;
    private String path;
    private int trytimes;


    public DownloadCallable(String path, List<Chapter> chapters ,int trytimes) {
        this.path = path;
        this.chapters = chapters;
        this.trytimes = trytimes;
    }


    @Override
    public String call() throws Exception {
        try (
                PrintWriter out = new PrintWriter(new File(path),"UTF-8");
        ) {
            for (Chapter chapter : chapters) {
                IChapterDetailSpider spider = ChapterDetailSpiderFactor.getChapterDetailSpider(chapter.getUrl());
                ChapterDetail detail = null;
                for (int i = 0; i < trytimes; i++) {
                    try {
                        detail = spider.getChapterDetail(chapter.getUrl());
                        out.println(detail.getTitle());
                        out.println(detail.getContent());
                        break;
                    }catch (RuntimeException e){
                        System.out.println("尝试第 " +(i+1) +"/"+trytimes +"次下载失败");
                    }

                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }
}