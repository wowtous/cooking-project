Create DATABASE mybatis;
USE mybatis;

DROP TABLE if EXISTS  book_content;
CREATE TABLE book_content (
  content_id bigint(20) not null auto_increment,
  content_name varchar(100) DEFAULT NULL ,
  content_url VARCHAR(100) not NULL ,
  status TINYINT(2) DEFAULT NULL COMMENT '0/null 未抓取 1 已抓取 2 链接失效 3 其他',
  insert_time datetime DEFAULT NULL ,
  update_time datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (content_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

