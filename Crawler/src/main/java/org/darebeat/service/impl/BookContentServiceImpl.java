package org.darebeat.service.impl;

import org.darebeat.mapper.BookContentMapper;
import org.darebeat.model.BookContent;
import org.darebeat.service.BookContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by darebeat on 4/30/17.
 */
@Service("bookContentServiceImpl")
public class BookContentServiceImpl implements BookContentService {
    /**
     * 使用@Autowired注解标注userMapper变量，
     * 当需要使用UserMapper时，Spring就会自动注入UserMapper
     */
    @Autowired
    private BookContentMapper mapper;//注入dao

    @Override
    public BookContent selectByUrl(String url) {
        return mapper.selectByUrl(url);
    }

    @Override
    public void insert(BookContent content) {
        mapper.insert(content);
    }

    @Override
    public void insertSelective(BookContent content) {
        mapper.insertSelective(content);
    }

    @Override
    public void updateStatus(String url,int status) {
        mapper.updateStatus(url,status);
    }
}
