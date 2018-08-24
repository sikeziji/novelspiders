package novel.spider.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import java.net.HttpCookie;
import java.net.URI;

/**
 * FileName: NovelSpiderHttpGet
 * Author:   Wangj
 * Date:     2018/8/17 10:47
 */
public class NovelSpiderHttpGet extends HttpGet {

    public  NovelSpiderHttpGet(){

    }
    public  NovelSpiderHttpGet(URI uri){
        super(uri);
    }
    public  NovelSpiderHttpGet(String uri){
        super(uri);
        setDefaultConfig();
    }


    /**
     * 设置默认的请求参数
     */
    private void setDefaultConfig() {
        this.setConfig(RequestConfig.custom()
                        .setSocketTimeout(2_000)
                .setConnectTimeout(10_000)
                .setConnectionRequestTimeout(10_000)
                .build()
        );
        this.setHeader("user-Agent","NobelSpider");
    }


}