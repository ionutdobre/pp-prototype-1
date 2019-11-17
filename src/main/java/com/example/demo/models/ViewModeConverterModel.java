package com.example.demo.models;

import com.example.demo.components.GenericBootstrapFormComponent;
import com.example.demo.constants.WebConstants;
import org.apache.wicket.model.IModel;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Converter for {@link GenericBootstrapFormComponent}
 * viewModeField This will be used when
 * {@link WebConstants#PARAM_PRINT} is true in the browser and will
 * convert the model object to something printable (string-like)
 */
public class ViewModeConverterModel<T> implements IModel<String> {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

    private final IModel<T> originalModel;

    public ViewModeConverterModel(final IModel<T> originalModel) {
        this.originalModel = originalModel;
    }

    @Override
    public String getObject() {
        final T object = originalModel.getObject();
        if (object == null) {
            return "";
        }

        // for booleans we return yes/no
        if (object instanceof Boolean) {
            return ((Boolean) object).booleanValue() ? "Yes" : "No";
        }

        // for collections that are empty, we return empty
        if (object instanceof Collection<?>) {
            if (((Collection<?>) object).size() == 0) {
                return "";
            }
        }

        // convert date to a nicer format
        if (object instanceof Date) {
            return SDF.format((Date) object);
        }

        // alas just return the string value of the object
        return object.toString();
    }

}
