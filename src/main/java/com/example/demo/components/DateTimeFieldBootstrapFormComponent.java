package com.example.demo.components;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.model.IModel;

import java.util.Date;

public class DateTimeFieldBootstrapFormComponent extends GenericBootstrapFormComponent<Date, DatetimePicker> {
    private static final long serialVersionUID = 6829640010904041758L;

    private static final String DEFAULT_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public DateTimeFieldBootstrapFormComponent(final String id, final IModel<String> labelModel,
                                               final IModel<Date> model) {
        super(id, labelModel, model);
    }

    public DateTimeFieldBootstrapFormComponent(final String id) {
        super(id);
    }

    public DateTimeFieldBootstrapFormComponent(final String id, final IModel<Date> model) {
        super(id, model);
    }

    @Override
    protected DatetimePicker inputField(final String id, final IModel<Date> model) {
        final DatetimePickerConfig config = new DatetimePickerConfig().withFormat(DEFAULT_FORMAT);
        return new DatetimePicker("field", initFieldModel(), config);
    }

    @Override
    public String getUpdateEvent() {
        return "update";
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        border.add(new AttributeModifier("style", "position:relative;"));

        IndicatingAjaxLink<String> clearDateLink = new IndicatingAjaxLink<String>("clearDate") {
            private static final long serialVersionUID = -1705495886974891511L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                DateTimeFieldBootstrapFormComponent.this.field.setModelObject(null);
                target.add(DateTimeFieldBootstrapFormComponent.this.field);
            }
        };
        border.add(clearDateLink);
    }
}
