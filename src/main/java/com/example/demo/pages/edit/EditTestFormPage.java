package com.example.demo.pages.edit;

import com.example.demo.components.CheckBoxBootstrapFormComponent;
import com.example.demo.components.CheckBoxPickerBootstrapFormComponent;
import com.example.demo.components.CheckBoxToggleBootstrapFormComponent;
import com.example.demo.components.ColorPickerBootstrapFormComponent;
import com.example.demo.components.ComponentUtil;
import com.example.demo.components.DateFieldBootstrapFormComponent;
import com.example.demo.components.DateTimeFieldBootstrapFormComponent;
import com.example.demo.components.Select2ChoiceBootstrapFormComponent;
import com.example.demo.components.Select2MultiChoiceBootstrapFormComponent;
import com.example.demo.components.TextAreaFieldBootstrapFormComponent;
import com.example.demo.components.TextFieldBootstrapFormComponent;
import com.example.demo.pages.BasePage;
import com.example.demo.persistence.categories.Department;
import com.example.demo.persistence.categories.Role;
import com.example.demo.persistence.test.TestForm;
import com.example.demo.providers.GenericPersistableJpaTextChoiceProvider;
import com.example.demo.services.DepartmentService;
import com.example.demo.services.RoleService;
import com.example.demo.services.TestFormService;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/editTestForm")
public class EditTestFormPage extends BasePage<TestForm> {

    @SpringBean
    private TestFormService jpaService;

    @SpringBean
    private RoleService roleService;

    @SpringBean
    private DepartmentService departmentService;

    private EditForm editForm;

    public EditTestFormPage(final PageParameters parameters) {
        super(parameters);

        editForm = new EditForm("editForm") {
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);

                if (ComponentUtil.isPrintMode()) {
                    Attributes.addClass(tag, "print-view");
                }
            }
        };

        final Long entityId = Long.valueOf(1);

        IModel<TestForm> model = new CompoundPropertyModel<>(jpaService.findById(entityId).orElse(null));
        if (model.getObject() == null) {
            final TestForm instance = jpaService.newInstance();
            if (instance != null) {
                model = new CompoundPropertyModel<>(instance);
            }
        }
        editForm.setCompoundPropertyModel(model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        // use this in order to avoid "ServletRequest does not contain multipart content" error
        // this error appears when we have a file upload component that is
        // hidden or not present in the page when the form is created
        editForm.setMultiPart(true);

        add(editForm);

        final TextFieldBootstrapFormComponent<String> textField = new TextFieldBootstrapFormComponent<>("textField");
        editForm.add(textField);
        textField.required();

        final TextAreaFieldBootstrapFormComponent<String> textArea =
                new TextAreaFieldBootstrapFormComponent<>("textArea");
        editForm.add(textArea);
        textArea.required();

        final Select2ChoiceBootstrapFormComponent<Department> entitySelect = new Select2ChoiceBootstrapFormComponent<>(
                "entitySelect", new GenericPersistableJpaTextChoiceProvider<>(departmentService));
        entitySelect.required();
        editForm.add(entitySelect);

        final Select2MultiChoiceBootstrapFormComponent<Role> entityMultiSelect =
                new Select2MultiChoiceBootstrapFormComponent<Role>(
                        "entityMultiSelect",
                        new GenericPersistableJpaTextChoiceProvider<Role>(roleService)
                );
        editForm.add(entityMultiSelect);

        final CheckBoxBootstrapFormComponent checkbox = new CheckBoxBootstrapFormComponent("checkbox");
        checkbox.required();
        editForm.add(checkbox);

        final CheckBoxPickerBootstrapFormComponent checkboxPicker =
                new CheckBoxPickerBootstrapFormComponent("checkboxPicker");
        checkboxPicker.required();
        editForm.add(checkboxPicker);

        final CheckBoxToggleBootstrapFormComponent checkboxToggle =
                new CheckBoxToggleBootstrapFormComponent("checkboxToggle");
        checkboxToggle.required();
        editForm.add(checkboxToggle);

        final DateFieldBootstrapFormComponent date = new DateFieldBootstrapFormComponent("date");
        date.required();
        editForm.add(date);

        final DateTimeFieldBootstrapFormComponent dateTime = new DateTimeFieldBootstrapFormComponent("dateTime");
        dateTime.required();
        editForm.add(dateTime);

        final ColorPickerBootstrapFormComponent colorPicker = new ColorPickerBootstrapFormComponent("colorPicker");
        colorPicker.required();
        editForm.add(colorPicker);
    }

    private CompoundPropertyModel<TestForm> compoundModel;

    public class EditForm extends BootstrapForm<TestForm> {
        /**
         * wrap the model with a {@link CompoundPropertyModel} to ease editing of fields
         */
        public void setCompoundPropertyModel(final IModel<TestForm> model) {
            compoundModel = new CompoundPropertyModel<>(model);
            setModel(compoundModel);
        }

        public EditForm(final String id, final IModel<TestForm> model) {
            this(id);
            setCompoundPropertyModel(model);
        }

        public EditForm(final String id) {
            super(id);

            setOutputMarkupId(true);
        }
    }
}
