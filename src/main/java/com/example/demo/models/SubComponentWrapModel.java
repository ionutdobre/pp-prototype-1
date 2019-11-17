package com.example.demo.models;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IWrapModel;

public class SubComponentWrapModel<T> implements IWrapModel<T> {
    private final Component parent;

    public SubComponentWrapModel(final Component parent) {
        this.parent = parent;
    }

    @Override
    public IModel<?> getWrappedModel() {
        return parent.getDefaultModel();
    }

    @Override
    public T getObject() {
        return (T) parent.getDefaultModelObject();
    }

    @Override
    public void setObject(final T object) {
        parent.setDefaultModelObject(object);
    }

    @Override
    public void detach() {
        final IModel<?> wrappedModel = getWrappedModel();
        if (wrappedModel != null) {
            wrappedModel.detach();
        }
    }
}
