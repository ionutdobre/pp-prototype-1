package com.example.demo;

import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = BootstrapProperties.PROPERTY_PREFIX)
public class BootstrapProperties extends BootstrapSettings {

    public static final String PROPERTY_PREFIX = "wicket.external.agilecoders.bootstrap";

    private boolean enabled = true;

    private BootswatchTheme theme = BootswatchTheme.Sandstone;

    public BootswatchTheme getTheme() {
        return theme;
    }

    public void setTheme(BootswatchTheme theme) {
        this.theme = theme;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}