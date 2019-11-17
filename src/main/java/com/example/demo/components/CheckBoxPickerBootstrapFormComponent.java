package com.example.demo.components;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonGroup;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstrapcheckbox.BootstrapCheckBoxPicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstrapcheckbox.BootstrapCheckBoxPickerConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.model.IModel;

public class CheckBoxPickerBootstrapFormComponent
        extends GenericBootstrapFormComponent<Boolean, BootstrapCheckBoxPicker> {

    public CheckBoxPickerBootstrapFormComponent(final String id, final IModel<String> labelModel,
            final IModel<Boolean> model) {
        super(id, labelModel, model);
    }

    public CheckBoxPickerBootstrapFormComponent(final String id, final IModel<Boolean> model) {
        super(id, model);
    }

    public CheckBoxPickerBootstrapFormComponent(final String id) {
        super(id);
    }

    @Override
    protected BootstrapCheckBoxPicker inputField(final String id, final IModel<Boolean> model) {

        final BootstrapCheckBoxPickerConfig config = new BootstrapCheckBoxPickerConfig().withOnClass("btn-info")
                .withOffClass("btn-warning")
                .withOnIcon(FontAwesomeIconType.thumbs_up).withOffIcon(FontAwesomeIconType.thumbs_down)
                .withReverse(true).withStyle(ButtonGroup.Size.Small);

        final BootstrapCheckBoxPicker checkBoxPicker = new BootstrapCheckBoxPicker("field", initFieldModel(), config);
        checkBoxPicker.add(new AjaxFormComponentUpdatingBehavior("change") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                CheckBoxPickerBootstrapFormComponent.this.onUpdate(target);
            }
        });

        return checkBoxPicker;
    }

    @Override
    public String getUpdateEvent() {
        return "click";
    }
}
