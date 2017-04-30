package org.darebeat.crawler;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.SpiderBean;
import org.springframework.stereotype.Service;

/**
 * Created by darebeat on 4/30/17.
 */
@Service("consolePipeline")
public class ConsolePipeline implements Pipeline<SpiderBean> {
    @Override
    public void process(SpiderBean spiderBean) {
        System.out.println(JSON.toJSONString(spiderBean));
    }
}
