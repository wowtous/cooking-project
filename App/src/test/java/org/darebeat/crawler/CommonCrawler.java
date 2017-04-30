package org.darebeat.crawler;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * Created by darebeat on 4/29/17.
 */
@Gecco(pipelines="consolePipeline")
public class CommonCrawler implements HtmlBean {
    private static final long serialVersionUID = -8870768223740844229L;

    @Request
    private HttpRequest request;

    @Text(own=false)
    @HtmlField(cssPath="body")
    private String body;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static void main(String[] args) {
        GeccoEngine.create()
                .classpath("org.darebeat.crawler")
                .start("http://maven.aliyun.com/nexus/")
                .interval(2000)
                .start();
    }
}
