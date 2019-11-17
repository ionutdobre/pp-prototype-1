package com.example.demo.components;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapCheckbox;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class CheckBoxBootstrapFormComponent extends GenericBootstrapFormComponent<Boolean, BootstrapCheckbox> {
    private CheckBox wrappedCheckbox;

    public CheckBoxBootstrapFormComponent(final String id, final IModel<String> labelModel,
                                          final IModel<Boolean> model) {
        super(id, labelModel, model);
    }

    public CheckBoxBootstrapFormComponent(final String id, final IModel<Boolean> model) {
        super(id, model);
    }

    public CheckBoxBootstrapFormComponent(final String id) {
        super(id);
    }

    @Override
    protected FormComponent<Boolean> updatingBehaviorComponent() {
        return wrappedCheckbox;
    }

    @Override
    protected BootstrapCheckbox inputField(final String id, final IModel<Boolean> model) {
        return new BootstrapCheckbox(id, initFieldModel(), Model.of()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected CheckBox newCheckBox(final String id, final IModel<Boolean> model) {
                wrappedCheckbox = super.newCheckBox(id, model);
                wrappedCheckbox.setOutputMarkupId(true);
                return wrappedCheckbox;
            }
        };
    }

    @Override
    public String getUpdateEvent() {
        return "click";
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        if (field.isRequired()) {
            wrappedCheckbox.setRequired(true);
        }
    }
}
