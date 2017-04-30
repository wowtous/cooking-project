package org.darebeat.crawler.book;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darebeat on 4/30/17.
 */
@PipelineName("contentPipeline")
public class ContentPipline implements Pipeline<Content> {
    public static List<HttpRequest> request = new ArrayList<HttpRequest>();

    @Override
    public void process(Content content) {
        List<HrefBean> cts = content.getDetails();
        if (null != cts){
            for (HrefBean ct : cts){
                String url = ct.getUrl();
                HttpRequest currRequest = content.getRequest();
                request.add(currRequest.subRequest(url));
            }
        }
    }
}
