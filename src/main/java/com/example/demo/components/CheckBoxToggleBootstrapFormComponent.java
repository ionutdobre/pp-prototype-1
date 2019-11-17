package com.example.demo.components;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstraptoggle.BootstrapToggle;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstraptoggle.BootstrapToggleConfig;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

public class CheckBoxToggleBootstrapFormComponent
        extends GenericBootstrapFormComponent<Boolean, BootstrapToggle> {
    private CheckBox wrappedCheckbox;

    public CheckBoxToggleBootstrapFormComponent(final String id, final IModel<String> labelModel,
                                                final IModel<Boolean> model) {
        super(id, labelModel, model);
    }

    public CheckBoxToggleBootstrapFormComponent(final String id, final IModel<Boolean> model) {
        super(id, model);
    }

    public CheckBoxToggleBootstrapFormComponent(final String id) {
        super(id);
    }

    @Override
    protected FormComponent<Boolean> updatingBehaviorComponent() {
        return wrappedCheckbox;
    }

    @Override
    protected BootstrapToggle inputField(final String id, final IModel<Boolean> model) {

        final BootstrapToggleConfig config = new BootstrapToggleConfig();
        config.withOnStyle(BootstrapToggleConfig.Style.info).withOffStyle(BootstrapToggleConfig.Style.warning)
                .withStyle("customCssClass");

        final BootstrapToggle checkBoxToggle = new BootstrapToggle("field", initFieldModel(), config) {
            @Override
            protected CheckBox newCheckBox(final String id, final IModel<Boolean> model) {
                wrappedCheckbox = super.newCheckBox(id, model);
                wrappedCheckbox.add(new AjaxFormComponentUpdatingBehavior("change") {

                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void onUpdate(final AjaxRequestTarget target) {
                        CheckBoxToggleBootstrapFormComponent.this.onUpdate(target);
                    }
                });
                return wrappedCheckbox;
            }
        };

        return checkBoxToggle;
    }

    @Override
    public String getUpdateEvent() {
        return "change";
    }
}
