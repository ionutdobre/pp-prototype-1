package com.example.demo.styles;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * The base CSS for the project.
 */
public class BaseStyles extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    public static final BaseStyles INSTANCE = new BaseStyles();

    public BaseStyles() {
        super(BaseStyles.class, "BaseStyles.css");
    }
}
