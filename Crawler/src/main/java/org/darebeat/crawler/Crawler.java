package org.darebeat.crawler;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.request.HttpGetRequest;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by darebeat on 4/30/17.
 */
@Service
public class Crawler implements ApplicationContextAware,InitializingBean {
    private ApplicationContext context;

    @Override
    public void afterPropertiesSet() throws Exception {
        PipelineFactory springPipelineFactory = (PipelineFactory)context.getBean("springPipelineFactory");
        HttpGetRequest start = new HttpGetRequest("http://www.xxbiquge.com/5_5422/");
        start.setCharset("UTF8");
        GeccoEngine.create()
                .classpath("org.darebeat.crawler.book")
                .pipelineFactory(springPipelineFactory)
                .interval(2000)
                .start(start)
                .run();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.in.read();
        context.close();
    }
}
