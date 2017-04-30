package org.darebeat.mapper;

import org.apache.ibatis.annotations.Param;
import org.darebeat.model.BookContent;
import org.omg.CORBA.StringHolder;

/**
 * Created by darebeat on 4/30/17.
 */
public interface BookContentMapper {
    BookContent selectByUrl(String url);
    int insert(BookContent content);
    int insertSelective(BookContent content);
    int updateStatus(@Param("content_url") String url,@Param("status") int status);
}
