package com.example.demo.pages;

import com.example.demo.styles.BaseStyles;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.markup.html.references.RespondJavaScriptReference;
import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.resource.JQueryResourceReference;

/**
 * @author idobre
 * @since 14/11/2019
 */
public class BasePage<T> extends GenericWebPage<T> {
    protected NotificationPanel feedbackPanel;

    public BasePage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        feedbackPanel = createFeedbackPanel();
        add(feedbackPanel);
    }

    private NotificationPanel createFeedbackPanel() {
        final NotificationPanel notificationPanel = new NotificationPanel("feedback");
        notificationPanel.setOutputMarkupId(true);
        return notificationPanel;
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        // Load Styles.
        response.render(CssHeaderItem.forReference(BaseStyles.INSTANCE));
        response.render(CssHeaderItem.forReference(BootstrapCssReference.instance()));
        response.render(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));

        // Load Scripts.
        response.render(RespondJavaScriptReference.headerItem());
        response.render(JavaScriptHeaderItem.forReference(JQueryResourceReference.getV2()));
    }
}
