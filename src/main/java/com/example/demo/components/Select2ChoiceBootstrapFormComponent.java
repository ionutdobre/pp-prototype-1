package com.example.demo.components;

import org.apache.wicket.model.IModel;
import org.wicketstuff.select2.ChoiceProvider;
import org.wicketstuff.select2.Select2BootstrapTheme;
import org.wicketstuff.select2.Select2Choice;

public class Select2ChoiceBootstrapFormComponent<TYPE>
        extends GenericBootstrapFormComponent<TYPE, Select2Choice<TYPE>> {

    public Select2ChoiceBootstrapFormComponent(final String id, final IModel<String> labelModel,
                                               final IModel<TYPE> model, final ChoiceProvider<TYPE> choiceProvider) {
        super(id, labelModel, model);
        provider(choiceProvider);
    }

    public Select2ChoiceBootstrapFormComponent<TYPE> provider(final ChoiceProvider<TYPE> choiceProvider) {
        field.setProvider(choiceProvider);
        return this;
    }

    public Select2ChoiceBootstrapFormComponent(final String id, final IModel<String> labelModel,
                                               final ChoiceProvider<TYPE> choiceProvider) {
        this(id, labelModel, null, choiceProvider);
    }

    public Select2ChoiceBootstrapFormComponent(final String id, final ChoiceProvider<TYPE> choiceProvider,
                                               final IModel<TYPE> model) {
        super(id, model);
        provider(choiceProvider);
    }

    public Select2ChoiceBootstrapFormComponent(final String id, final ChoiceProvider<TYPE> choiceProvider) {
        super(id);
        provider(choiceProvider);
    }

    @Override
    protected Select2Choice<TYPE> inputField(final String id, final IModel<TYPE> model) {
        return new Select2Choice<>(id, initFieldModel());
    }

    @Override
    public String getUpdateEvent() {
        return "change";
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        field.getSettings().setPlaceholder("Click to select");
        field.getSettings().setAllowClear(true);
        field.getSettings().setCloseOnSelect(true);
        field.getSettings().setDropdownAutoWidth(true);
        field.getSettings().setTheme(new Select2BootstrapTheme(false));
    }
}
