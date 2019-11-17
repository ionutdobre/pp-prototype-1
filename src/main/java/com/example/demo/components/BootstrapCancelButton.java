package com.example.demo.components;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda.LaddaAjaxButton;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

public abstract class BootstrapCancelButton extends LaddaAjaxButton {
    public BootstrapCancelButton(final String id, final IModel<String> model) {
        super(id, model, Buttons.Type.Default);
        setDefaultFormProcessing(false);
        setIconType(FontAwesomeIconType.ban);
    }

    @Override
    protected abstract void onSubmit(AjaxRequestTarget target);

    @Override
    protected void onInitialize() {
        super.onInitialize();
        if (ComponentUtil.isPrintMode()) {
            setVisibilityAllowed(false);
        }
    }
}
