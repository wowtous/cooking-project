package org.darebeat.crawler.book;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;
import org.darebeat.model.BookContent;
import org.darebeat.service.BookContentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darebeat on 4/30/17.
 */
@Service("contentPipeline")
public class ContentPipline implements Pipeline<Content> {
    @Resource(name = "bookContentServiceImpl")
    private BookContentService bcs;

    @Override
    public void process(Content content) {
        List<HrefBean> cts = content.getDetails();
        if (null != cts){
            for (HrefBean ct : cts){
                BookContent bc = new BookContent();
                bc.setContent_url(ct.getUrl());
                bc.setContent_name(ct.getTitle());
                bc.setStatus(0);
                bc.setInsert_time(new Timestamp(System.currentTimeMillis()));
                if (bcs.selectByUrl(ct.getUrl())==null){
                    bcs.insertSelective(bc);
                }
                System.out.println(bc.toString());
            }
        }
    }
}
