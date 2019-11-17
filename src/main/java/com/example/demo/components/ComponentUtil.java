package com.example.demo.components;

import com.example.demo.constants.WebConstants;
import com.example.demo.events.EditingDisabledEvent;
import com.example.demo.events.EditingEnabledEvent;
import com.example.demo.persistence.GenericPersistable;
import com.example.demo.persistence.Labelable;
import com.example.demo.providers.GenericPersistableJpaTextChoiceProvider;
import com.example.demo.services.TextSearchableService;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author idobre
 * @since 2019-03-04
 */
public final class ComponentUtil {
    private static final DecimalFormat DF = new DecimalFormat("#,###.###");

    private ComponentUtil() {

    }

    public static Date getDateFromLocalDate(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Returns true if the {@link WebConstants#PARAM_PRINT} is used as a parameter
     *
     * @return
     */
    public static boolean isPrintMode() {
        return RequestCycle.get().getRequest().getRequestParameters().getParameterValue(WebConstants.PARAM_PRINT)
                .toBoolean(false);
    }

    public static void enableDisableEvent(final Component c, final IEvent<?> event) {
        if (event.getPayload() instanceof EditingDisabledEvent) {
            c.setEnabled(false);
        }

        if (event.getPayload() instanceof EditingEnabledEvent) {
            c.setEnabled(true);
        }

    }

    public static IValidator<? super String> isEmail() {
        return EmailAddressValidator.getInstance();
    }

    public static CheckBoxBootstrapFormComponent addCheckBox(
            final WebMarkupContainer parent,
            final String id) {
        final CheckBoxBootstrapFormComponent checkBox = new CheckBoxBootstrapFormComponent(id);
        parent.add(checkBox);

        return checkBox;
    }

    public static CheckBoxToggleBootstrapFormComponent addCheckToggle(
            final WebMarkupContainer parent,
            final String id) {
        final CheckBoxToggleBootstrapFormComponent checkToggle = new CheckBoxToggleBootstrapFormComponent(id);
        parent.add(checkToggle);

        return checkToggle;
    }

    public static CheckBoxYesNoToggleBootstrapFormComponent addYesNoToggle(
            final WebMarkupContainer parent,
            final String id) {
        final CheckBoxYesNoToggleBootstrapFormComponent checkToggle = new CheckBoxYesNoToggleBootstrapFormComponent(id);
        parent.add(checkToggle);

        return checkToggle;
    }

    public static TextAreaFieldBootstrapFormComponent<String> addTextAreaField(
            final WebMarkupContainer parent,
            final String id) {
        final TextAreaFieldBootstrapFormComponent<String> textAreaField = new TextAreaFieldBootstrapFormComponent<>(id);
        parent.add(textAreaField);

        return textAreaField;
    }

    public static TextFieldBootstrapFormComponent<String> addTextField(
            final WebMarkupContainer parent,
            final String id) {
        final TextFieldBootstrapFormComponent<String> textField = new TextFieldBootstrapFormComponent<>(id);
        parent.add(textField);

        return textField;
    }

    public static TextFieldBootstrapFormComponent<String> addTextLoginField(
            final WebMarkupContainer parent,
            final String id) {
        final TextFieldBootstrapFormComponent<String> textField = new TextFieldBootstrapFormComponent<String>(id) {
            @Override
            public String getUpdateEvent() {
                return null;
            }
        };
        parent.add(textField);

        return textField;
    }

    public static PasswordFieldBootstrapFormComponent addTextPasswordField(
            final WebMarkupContainer parent,
            final String id) {
        final PasswordFieldBootstrapFormComponent textField = new PasswordFieldBootstrapFormComponent(id) {
            @Override
            public String getUpdateEvent() {
                return null;
            }
        };
        parent.add(textField);

        return textField;
    }

    public static TextFieldBootstrapFormComponent<Integer> addIntegerTextField(
            final WebMarkupContainer parent,
            final String id) {
        final TextFieldBootstrapFormComponent<Integer> textField = new TextFieldBootstrapFormComponent<>(id);
        textField.integer();
        parent.add(textField);

        return textField;
    }

    public static TextFieldBootstrapFormComponent<Long> addLongTextField(
            final WebMarkupContainer parent,
            final String id) {
        final TextFieldBootstrapFormComponent<Long> textField = new TextFieldBootstrapFormComponent<>(id);
        textField.longValue();
        parent.add(textField);

        return textField;
    }

    public static TextFieldBootstrapFormComponent<BigDecimal> addBigDecimalField(
            final WebMarkupContainer parent,
            final String id) {
        final TextFieldBootstrapFormComponent<BigDecimal> textField = new TextFieldBootstrapFormComponent<>(id);
        textField.decimal();
        parent.add(textField);

        return textField;
    }

    public static TextFieldBootstrapFormComponent<Double> addDoubleField(
            final WebMarkupContainer parent,
            final String id) {
        final TextFieldBootstrapFormComponent<Double> textField = new TextFieldBootstrapFormComponent<>(id);
        textField.asDouble();
        parent.add(textField);

        return textField;
    }

    public static DateTimeFieldBootstrapFormComponent addDateTimeField(
            final WebMarkupContainer parent,
            final String id) {
        final DateTimeFieldBootstrapFormComponent field = new DateTimeFieldBootstrapFormComponent(id);
        parent.add(field);

        return field;
    }

    public static DateFieldBootstrapFormComponent addDateField(
            final WebMarkupContainer parent,
            final String id) {
        final DateFieldBootstrapFormComponent field = new DateFieldBootstrapFormComponent(id);
        parent.add(field);

        return field;
    }

    public static <E extends GenericPersistable & Labelable & Serializable> Select2ChoiceBootstrapFormComponent<E>
    addSelect2ChoiceField(
            final WebMarkupContainer parent,
            final String id,
            final TextSearchableService<E> searchService) {
        final GenericPersistableJpaTextChoiceProvider<E> choiceProvider
                = new GenericPersistableJpaTextChoiceProvider<>(searchService);
        final Select2ChoiceBootstrapFormComponent<E> component = new Select2ChoiceBootstrapFormComponent<>(id,
                choiceProvider);
        parent.add(component);

        return component;
    }

    public static <E extends GenericPersistable & Labelable & Serializable> Select2MultiChoiceBootstrapFormComponent<E>
    addSelect2MultiChoiceField(
            final WebMarkupContainer parent,
            final String id,
            final TextSearchableService<E> searchService) {
        final GenericPersistableJpaTextChoiceProvider<E> choiceProvider =
                new GenericPersistableJpaTextChoiceProvider<>(searchService);
        final Select2MultiChoiceBootstrapFormComponent<E> component =
                new Select2MultiChoiceBootstrapFormComponent<>(id, choiceProvider);
        parent.add(component);

        return component;
    }

    /**
     * Trivial method to set the child {@link GenericBootstrapFormComponent}
     * required when added to the parent {@link WebMarkupContainer}
     *
     * @param requiredFlag the {@link FormComponent#setRequired(boolean)}
     * @param parent
     * @param child
     * @return the parent
     */
    public static MarkupContainer addRequiredFlagBootstrapFormComponent(
            final boolean requiredFlag,
            final WebMarkupContainer parent,
            final GenericBootstrapFormComponent<?, ?> child) {
        return parent.add(requiredFlag ? child.required() : child);
    }

    public static String formatNumber(final BigDecimal number) {
        return DF.format(number);
    }
}
