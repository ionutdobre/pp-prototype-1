package com.example.demo.components;

import com.example.demo.models.SubComponentWrapModel;
import com.example.demo.models.ViewModeConverterModel;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipConfig;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.InputBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.attributes.ThrottlingSettings;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.time.Duration;


public abstract class GenericBootstrapFormComponent<TYPE, FIELD extends FormComponent<TYPE>> extends FieldPanel<TYPE> {
    // prevents repainting of select boxes and other problems with triggering the update
    // even while the component js is not done updating.
    private static final int THROTTLE_UPDATE_DELAY_MS = 200;

    protected FormGroup border;

    protected FIELD field;

    private TooltipConfig.OpenTrigger configWithTrigger = TooltipConfig.OpenTrigger.hover;

    // use a flag if we need to display a Tooltip since StringResourceModel it's expensive
    private Boolean showTooltip = false;

    private final IModel<String> labelModel;

    public GenericBootstrapFormComponent(final String id) {
        this(id, null);
    }

    public GenericBootstrapFormComponent(final String id, final IModel<TYPE> model) {
        this(id, new ResourceModel(id + ".label"), model);
    }

    public GenericBootstrapFormComponent(final String id, final IModel<String> labelModel, final IModel<TYPE> model) {
        super(id, model);

        this.labelModel = labelModel;

        border = new FormGroup("enclosing-field-group");
        border.setOutputMarkupId(true);
        add(border);
        initializeField();
    }

    public GenericBootstrapFormComponent<TYPE, FIELD> type(final Class<?> clazz) {
        field.setType(clazz);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        if ((field instanceof RadioGroup) || (field instanceof CheckGroup)) {
            getAjaxFormChoiceComponentUpdatingBehavior();
        } else {
            getAjaxFormComponentUpdatingBehavior();
        }

        final Label viewModeField = new Label("viewModeField", new ViewModeConverterModel<>(getModel()));
        viewModeField.setEscapeModelStrings(false);
        viewModeField.setVisibilityAllowed(isPrintMode());
        border.add(viewModeField);

        if (showTooltip) {
            final TooltipLabel tooltipLabel = new TooltipLabel("tooltipLabel", getId());
            tooltipLabel.setVisibilityAllowed(showTooltip);
            tooltipLabel.setConfigWithTrigger(configWithTrigger);
            border.add(tooltipLabel);
        } else {
            border.add(new TransparentWebMarkupContainer("tooltipLabel").setVisibilityAllowed(false));
        }

        add(new TransparentWebMarkupContainer("revisions").setVisibilityAllowed(false)); // this is just a placeholder
    }

    protected void initializeField() {
        field = inputField("field", getModel());
        field.setOutputMarkupId(true);
        field.setVisibilityAllowed(!isPrintMode());
        final InputBehavior sizeBehavior = getInputBehavior();
        if (sizeBehavior != null) {
            field.add(sizeBehavior);
        }

        border.add(field);
        field.setLabel(labelModel);
    }

    protected abstract FIELD inputField(String id, IModel<TYPE> model);

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);

        // add a new class for required fields
        if (field.isRequired()) {
            Attributes.addClass(tag, "required");
        }
    }

    @Override
    public void onEvent(final IEvent<?> event) {
        ComponentUtil.enableDisableEvent(this, event);
    }

    protected IModel<TYPE> initFieldModel() {
        if (getDefaultModel() == null) {
            return new SubComponentWrapModel<>(this);
        }
        return (IModel<TYPE>) getDefaultModel();
    }

    /**
     * True if the control can print contents unescaped when in readonly mode
     */
    protected boolean printUnescaped() {
        return false;
    }

    /**
     * use this behavior for choices/groups that are not one component in the html but many.
     */
    private void getAjaxFormChoiceComponentUpdatingBehavior() {
        updatingBehaviorComponent().add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
                attributes.setThrottlingSettings(new ThrottlingSettings(
                        Duration.milliseconds(THROTTLE_UPDATE_DELAY_MS)));
                super.updateAjaxAttributes(attributes);
            }

            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                GenericBootstrapFormComponent.this.onUpdate(target);
            }
        });
    }

    /**
     * This is the component that has to be updated with the
     * {@link #getAjaxFormChoiceComponentUpdatingBehavior()} or with
     * {@link #getAjaxFormComponentUpdatingBehavior()}. It usuall is the field,
     * but the field may be a wrapper, in which case you should override this
     * and provide the wrapped field.
     */
    protected FormComponent<TYPE> updatingBehaviorComponent() {
        return field;
    }

    protected void getAjaxFormComponentUpdatingBehavior() {
        if (getUpdateEvent() == null) {
            return;
        }
        updatingBehaviorComponent().add(new AjaxFormComponentUpdatingBehavior(getUpdateEvent()) {
            private static final long serialVersionUID = -2696538086634114609L;

            @Override
            protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
                attributes.setThrottlingSettings(new ThrottlingSettings(
                        Duration.milliseconds(THROTTLE_UPDATE_DELAY_MS)));
                super.updateAjaxAttributes(attributes);
            }

            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                target.add(border);
                GenericBootstrapFormComponent.this.onUpdate(target);
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final RuntimeException e) {
                target.add(border);
            }
        });
    }

    public String getUpdateEvent() {
        return "blur";
    }

    protected InputBehavior getInputBehavior() {
        return new InputBehavior(InputBehavior.Size.Medium);
    }

    private boolean isPrintMode() {
        return ComponentUtil.isPrintMode();
    }

    public GenericBootstrapFormComponent<TYPE, FIELD> hideLabel() {
        field.setLabel(null);
        return this;
    }

    public GenericBootstrapFormComponent<TYPE, FIELD> required() {
        field.setRequired(true);
        return this;
    }

    protected void onUpdate(final AjaxRequestTarget target) {
    }

    public FIELD getField() {
        return field;
    }

    public FormGroup getBorder() {
        return border;
    }

    public IModel<String> getLabelModel() {
        return labelModel;
    }

    public TooltipConfig.OpenTrigger getConfigWithTrigger() {
        return configWithTrigger;
    }

    public void setConfigWithTrigger(final TooltipConfig.OpenTrigger configWithTrigger) {
        this.configWithTrigger = configWithTrigger;
    }

    public void setShowTooltip(final Boolean showTooltip) {
        this.showTooltip = showTooltip;
    }
}
