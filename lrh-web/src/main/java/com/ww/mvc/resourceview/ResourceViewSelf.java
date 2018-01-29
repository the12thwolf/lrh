package com.ww.mvc.resourceview;

import org.springframework.web.servlet.view.InternalResourceView;

import java.io.File;
import java.util.Locale;

/**
 * Created by Administrator on 2017/11/6.
 */
public class ResourceViewSelf extends InternalResourceView {
    @Override
    public boolean checkResource(Locale locale) {
        String url=this.getServletContext().getRealPath("/") + getUrl();
        //System.out.println("ResourceViewSelf 的url="+url);
        File file = new File(url);
        return file.exists();// 判断该页面是否存在
    }
}
