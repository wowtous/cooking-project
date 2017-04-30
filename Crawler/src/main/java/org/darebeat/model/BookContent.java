package org.darebeat.model;

import java.sql.Timestamp;

/**
 * Created by darebeat on 4/30/17.
 */
public class BookContent {
    private int content_id;
    private String content_name;
    private String content_url;
    private int status;
    private Timestamp insert_time;
    private Timestamp update_time;

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getContent_name() {
        return content_name;
    }

    public void setContent_name(String content_name) {
        this.content_name = content_name;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Timestamp insert_time) {
        this.insert_time = insert_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "BookContent{" +
                "content_id=" + content_id +
                ", content_name='" + content_name + '\'' +
                ", content_url='" + content_url + '\'' +
                ", status=" + status +
                ", insert_time=" + insert_time +
                ", update_time=" + update_time +
                '}';
    }


}
