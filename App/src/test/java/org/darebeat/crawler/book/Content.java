package org.darebeat.crawler.book;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * Created by darebeat on 4/30/17.
 */
@Gecco(matchUrl = "http://www.xxbiquge.com/{code}/", pipelines={"consolePipeline", "contentPipeline"})
public class Content implements HtmlBean {

    @Request
    private HttpRequest request;

    @RequestParameter
    private String code;

    @HtmlField(cssPath="#list dd a")
    private List<HrefBean> details;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<HrefBean> getDetails() {
        return details;
    }

    public void setDetails(List<HrefBean> details) {
        this.details = details;
    }

    public static void main(String[] args) {
        HttpRequest start = new HttpGetRequest("http://www.xxbiquge.com/5_5422/");
        start.setCharset("UTF8");
        GeccoEngine.create()
                .classpath("org.darebeat.crawler.book")
                //开始抓取的页面地址
                .start(start)
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(1000)
                .run();

        GeccoEngine.create()
                .classpath("org.darebeat.crawler.book")
                //开始抓取的页面地址
                .start(ContentPipline.request)
                //开启几个爬虫线程
                .thread(3)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .start();
    }
}
