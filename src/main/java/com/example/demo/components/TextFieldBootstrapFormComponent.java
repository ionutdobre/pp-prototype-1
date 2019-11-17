package com.example.demo.components;

import com.example.demo.constants.WebConstants;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;

import java.math.BigDecimal;

public class TextFieldBootstrapFormComponent<TYPE> extends GenericBootstrapFormComponent<TYPE, TextField<TYPE>> {
    private static final StringValidator VALIDATOR =
            WebConstants.StringValidators.MAXIMUM_LENGTH_VALIDATOR_ONE_LINE_TEXT;

    public TextFieldBootstrapFormComponent(final String id, final IModel<String> labelModel, final IModel<TYPE> model) {
        super(id, labelModel, model);
    }

    public TextFieldBootstrapFormComponent(final String id, final IModel<TYPE> model) {
        super(id, model);
    }

    public TextFieldBootstrapFormComponent(final String id) {
        super(id);
    }

    @Override
    protected TextField<TYPE> inputField(final String id, final IModel<TYPE> model) {
        return new TextField<>(id, initFieldModel());
    }

    public TextFieldBootstrapFormComponent<TYPE> integer() {
        field.setType(Integer.class);
        return this;
    }

    public TextFieldBootstrapFormComponent<TYPE> longValue() {
        field.setType(Long.class);
        return this;
    }

    public TextFieldBootstrapFormComponent<TYPE> decimal() {
        field.setType(BigDecimal.class);
        return this;
    }

    public TextFieldBootstrapFormComponent<TYPE> asDouble() {
        field.setType(Double.class);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        if (getField().getType() == null || !Number.class.isAssignableFrom(getField().getType())) {
            getField().add(VALIDATOR);
        }
    }
}
