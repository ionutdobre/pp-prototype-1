package com.example.demo.components;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.ColorPickerTextField;
import org.apache.wicket.model.IModel;

public class ColorPickerBootstrapFormComponent extends GenericBootstrapFormComponent<String, ColorPickerTextField> {
    public ColorPickerBootstrapFormComponent(final String id, final IModel<String> labelModel,
                                             final IModel<String> model) {
        super(id, labelModel, model);
    }

    public ColorPickerBootstrapFormComponent(final String id, final IModel<String> model) {
        super(id, model);
    }

    public ColorPickerBootstrapFormComponent(final String id) {
        super(id);
    }

    @Override
    protected ColorPickerTextField inputField(final String id, final IModel<String> model) {
        return new ColorPickerTextField(id, initFieldModel());
    }
}
