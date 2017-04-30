package org.darebeat;

import org.darebeat.model.BookContent;
import org.darebeat.service.BookContentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;

/**
 * Created by darebeat on 4/30/17.
 */
public class BookContentTest {
    private BookContentService bcs;
    private String url = "http://www.xxbiquge.com/5_5422/10182756.html";

    @Before
    public void before(){
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        bcs = (BookContentService) ac.getBean("bookContentService");
    }

    @Test
    public void testInsertContent(){
        BookContent bb = bcs.selectByUrl(url);
        if(bb == null){
            BookContent bc = new BookContent();
            bc.setContent_id(1);
            bc.setContent_name("第1138章 活着离开");
            bc.setContent_url(url);
            bc.setStatus(0);
            bc.setInsert_time(new Timestamp(System.currentTimeMillis()));
            bcs.insert(bc);
        }
    }

    @Test
    public void testSelect(){
        bcs.selectByUrl(url);
    }

    @Test
    public void testUpdate(){
        bcs.updateStatus(url,1);
    }
}
