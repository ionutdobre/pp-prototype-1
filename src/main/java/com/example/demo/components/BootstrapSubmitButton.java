package com.example.demo.components;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda.LaddaAjaxButton;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

public abstract class BootstrapSubmitButton extends LaddaAjaxButton {

    public BootstrapSubmitButton(final String id, final IModel<String> model) {
        super(id, model, Buttons.Type.Primary);
        setIconType(FontAwesomeIconType.save);
    }

    public BootstrapSubmitButton(final String id, final Form<?> form, final IModel<String> model) {
        super(id, model, form, Buttons.Type.Primary);
        setIconType(FontAwesomeIconType.save);
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
