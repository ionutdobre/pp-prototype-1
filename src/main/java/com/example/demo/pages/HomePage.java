package com.example.demo.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author idobre
 * @since 14/11/2019
 */
@WicketHomePage
@MountPath("/")
public class HomePage extends BasePage<Void> {

    public HomePage(PageParameters parameters) {
        super(parameters);
    }
}