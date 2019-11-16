package com.example.demo.pages;

import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author idobre
 * @since 14/11/2019
 */
public class BasePage<T> extends GenericWebPage<T> {
    public BasePage(final PageParameters parameters) {
        super(parameters);
    }
}
