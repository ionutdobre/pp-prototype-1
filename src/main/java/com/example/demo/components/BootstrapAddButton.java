package com.example.demo.components;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Size;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda.LaddaAjaxButton;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.model.IModel;

public abstract class BootstrapAddButton extends LaddaAjaxButton {

    private static final long serialVersionUID = 8306451874943978003L;

    public BootstrapAddButton(final String id, final IModel<String> model) {
        super(id, model, Buttons.Type.Info);
        setIconType(FontAwesomeIconType.save);
        setDefaultFormProcessing(false);
        setIconType(FontAwesomeIconType.plus).setSize(Size.Medium).setLabel(model);
        setOutputMarkupPlaceholderTag(true);
    }

    @Override
    protected abstract void onSubmit(AjaxRequestTarget target);

    @Override
    public void onEvent(final IEvent<?> event) {
        ComponentUtil.enableDisableEvent(this, event);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        if (ComponentUtil.isPrintMode()) {
            setVisibilityAllowed(false);
        }
    }

}
