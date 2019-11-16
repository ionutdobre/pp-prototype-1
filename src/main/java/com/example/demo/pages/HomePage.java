package com.example.demo.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author idobre
 * @since 14/11/2019
 */
@WicketHomePage
@MountPath("/")
public class HomePage extends BasePage<Void> {
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    public HomePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        logger.info(">>> init!");
    }
}