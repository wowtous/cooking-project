package org.darebeat.wx;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * Created by darebeat on 4/30/17.
 */
@Gecco(matchUrl="http://weixin.sogou.com/weixin?type=2&query={keyword}", pipelines={"consolePipeline"})
public class SogouWX implements HtmlBean {

    private static final long serialVersionUID = 7504646787612579665L;

    @RequestParameter
    private String keyword;

    @HtmlField(cssPath=".news-box .news-list")
    private List<WeiXin> weixins;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<WeiXin> getWeixins() {
        return weixins;
    }

    public void setWeixins(List<WeiXin> weixins) {
        this.weixins = weixins;
    }

    public static void main(String[] args) {
        HttpGetRequest start = new HttpGetRequest("http://weixin.sogou.com/weixin?type=2&query=%E6%B7%B1%E5%9C%B3");
        start.addCookie("SNUID", "8F0767E48F8ADEC0B17B407D9030539A");
        start.addCookie("SUID", "81CB58724E6C860A57DC9EE1000CEC38");
        start.addCookie("IPLOC", "CN3101");
//        start.addCookie("SUV", "009021CB74E90E0857DD605835D56388");
        start.addHeader("Host", "weixin.sogou.com");
        start.addHeader("Upgrade-Insecure-Requests", "1");
        start.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        start.addHeader("Accept-Encoding", "gzip, deflate, sdch");
        start.addHeader("Cache-Control", "max-age=0");
        GeccoEngine.create()
                .classpath("org.darebeat.wx")
                .start(start)
                .interval(5000)
                .run();
    }
}
