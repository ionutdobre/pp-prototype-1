package com.example.demo.components;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

public class FieldPanel<T> extends GenericPanel<T> {

    public FieldPanel(final String id) {
        super(id);
    }

    public FieldPanel(final String id, final IModel<T> model) {
        super(id, model);
    }

}
