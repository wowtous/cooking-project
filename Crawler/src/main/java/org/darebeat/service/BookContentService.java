package org.darebeat.service;

import org.darebeat.model.BookContent;

/**
 * Created by darebeat on 4/30/17.
 */
public interface BookContentService {
    BookContent selectByUrl(String url);
    void insert(BookContent content);
    void insertSelective(BookContent content);
    void updateStatus(String url,int status);
}
