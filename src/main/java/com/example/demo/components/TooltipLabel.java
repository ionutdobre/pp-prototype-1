package com.example.demo.components;

import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipConfig;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

public class TooltipLabel extends Label {
    private static final long serialVersionUID = 1L;

    private StringResourceModel helpModelText;

    private String fieldId;

    private boolean configWithHtml = true;

    private TooltipConfig.OpenTrigger configWithTrigger = TooltipConfig.OpenTrigger.hover;

    private static CustomTooltipConfig tooltipConfig;

    public class CustomTooltipConfig extends TooltipConfig {
        private static final long serialVersionUID = 1L;

        public CustomTooltipConfig() {
            if (configWithHtml) {
                withHtml(true);
            }
            withTrigger(configWithTrigger);
        }
    }

    public TooltipLabel(final String id, final String fieldId) {
        super(id, Model.of(""));
        this.fieldId = fieldId;
        add(AttributeModifier.append("class", "fa fa-question-circle"));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        helpModelText = new StringResourceModel(fieldId + ".help", this);
        helpModelText.setDefaultValue("");

        if (!helpModelText.getString().isEmpty()) {
            tooltipConfig = new CustomTooltipConfig();
            add(new TooltipBehavior(helpModelText, tooltipConfig));
            setVisibilityAllowed(!ComponentUtil.isPrintMode());
        } else {
            setVisibilityAllowed(false);
        }
    }

    public TooltipConfig.OpenTrigger getConfigWithTrigger() {
        return configWithTrigger;
    }

    public void setConfigWithTrigger(final TooltipConfig.OpenTrigger configWithTrigger) {
        this.configWithTrigger = configWithTrigger;
    }

    public boolean isConfigWithHtml() {
        return configWithHtml;
    }

    public void setConfigWithHtml(final boolean configWithHtml) {
        this.configWithHtml = configWithHtml;
    }
}
