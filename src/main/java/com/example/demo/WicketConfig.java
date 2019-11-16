package com.example.demo;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.less.BootstrapLess;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import org.apache.wicket.ajax.AjaxNewWindowNotifyingBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author idobre
 * @since 14/11/2019
 */
@ApplicationInitExtension
@ConditionalOnProperty(prefix = BootstrapProperties.PROPERTY_PREFIX, value = "enabled", matchIfMissing = true)
@ConditionalOnClass(WicketConfig.class)
@EnableConfigurationProperties({BootstrapProperties.class})
public class WicketConfig implements WicketApplicationInitConfiguration {

    @Autowired
    private BootstrapProperties prop;

    @Override
    public void init(final WebApplication webApplication) {
        final ThemeProvider themeProvider = new BootswatchThemeProvider(prop.getTheme());
        prop.setThemeProvider(themeProvider);

        Bootstrap.install(webApplication, prop);
        BootstrapLess.install(webApplication);

        webApplication.getComponentInitializationListeners().add(component -> {
            if (component instanceof WebPage) {
                component.add(new AjaxNewWindowNotifyingBehavior());
            }
        });
    }
}
