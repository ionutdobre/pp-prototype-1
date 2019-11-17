package com.example.demo.components;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.IModel;

public class PasswordFieldBootstrapFormComponent extends GenericBootstrapFormComponent<String, PasswordTextField> {
    public PasswordFieldBootstrapFormComponent(final String id, final IModel<String> labelModel,
                                               final IModel<String> model) {
        super(id, labelModel, model);
    }

    public PasswordFieldBootstrapFormComponent(final String id, final IModel<String> model) {
        super(id, model);
    }

    public PasswordFieldBootstrapFormComponent(final String id) {
        super(id);
    }

    @Override
    protected PasswordTextField inputField(final String id, final IModel<String> model) {
        return new PasswordTextField(id, initFieldModel());
    }
}
