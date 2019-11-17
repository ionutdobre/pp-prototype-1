package com.example.demo.components;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda.LaddaAjaxButton;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract class BootstrapDeleteButton extends LaddaAjaxButton {

    public BootstrapDeleteButton(final String id, final IModel<String> model) {
        super(id, model, Buttons.Type.Danger);
    }

    public BootstrapDeleteButton(final String id) {
        super(id, Buttons.Type.Danger);
    }

    @Override
    protected abstract void onSubmit(AjaxRequestTarget target);

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new AttributeAppender("onclick", new Model<>("window.onbeforeunload = null;"), " "));
        setDefaultFormProcessing(false);
        setIconType(FontAwesomeIconType.trash_o);

        if (ComponentUtil.isPrintMode()) {
            setVisibilityAllowed(false);
        }
    }

    @Override
    public void onEvent(final IEvent<?> event) {
        ComponentUtil.enableDisableEvent(this, event);
    }

    @Override
    protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {

        super.updateAjaxAttributes(attributes);
        AjaxCallListener ajaxCallListener = new AjaxCallListener();
        ajaxCallListener.onPrecondition("return confirm('Confirm Delete?');");
        attributes.getAjaxCallListeners().add(ajaxCallListener);
    }
}
