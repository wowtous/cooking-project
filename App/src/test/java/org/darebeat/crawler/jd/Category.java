package org.darebeat.crawler.jd;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * Created by darebeat on 4/29/17.
 */
public class Category implements HtmlBean {

    private static final long serialVersionUID = 3018760488621382659L;

    @Text
    @HtmlField(cssPath="dt a")
    private String parentName;

    @HtmlField(cssPath="dd a")
    private List<HrefBean> categorys;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<HrefBean> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<HrefBean> categorys) {
        this.categorys = categorys;
    }

}
