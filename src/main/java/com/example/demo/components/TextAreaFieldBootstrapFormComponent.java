package com.example.demo.components;

import com.example.demo.constants.WebConstants;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;

public class TextAreaFieldBootstrapFormComponent<TYPE> extends GenericBootstrapFormComponent<TYPE, TextArea<TYPE>> {
    private StringValidator validator = WebConstants.StringValidators.MAXIMUM_LENGTH_VALIDATOR_ONE_LINE_TEXTAREA;

    public TextAreaFieldBootstrapFormComponent(final String id, final IModel<String> labelModel,
                                               final IModel<TYPE> model) {
        super(id, labelModel, model);
    }

    public TextAreaFieldBootstrapFormComponent(final String id, final IModel<String> labelModel) {
        super(id, labelModel, null);
    }

    public TextAreaFieldBootstrapFormComponent(final String id) {
        super(id);
    }

    @Override
    protected TextArea<TYPE> inputField(final String id, final IModel<TYPE> model) {
        TextArea<TYPE> textArea = new TextArea<TYPE>(id, initFieldModel());
        return textArea;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        getField().add(validator);
    }
}
