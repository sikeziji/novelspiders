package novel.spider.interfaces;


import novel.spider.configuration.configuration;

public interface INovelDownload {

    /**
     *如说我下载到D:/novel/biquge.tw/完美世界/完美世界.txt
     * @param url 这个url是指某本小说的章节列表页面
     * @param configuration
     * @return
     */
    public String download(String url, configuration configuration );
}
