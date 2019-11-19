package com.example.demo.persistence.test;

import com.example.demo.constants.DBConstants;
import com.example.demo.persistence.GenericPersistable;
import com.example.demo.persistence.categories.Brand;
import com.example.demo.persistence.categories.Role;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class TestForm extends GenericPersistable {
    private String textField;

    @Column(length = DBConstants.MAX_DEFAULT_TEXT_LENGTH)
    private String textArea;

    private Boolean checkbox;

    private Boolean checkboxPicker;

    private Boolean checkboxToggle;

    private String colorPicker;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToOne
    private Brand entitySelect;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany
    private List<Role> entityMultiSelect;

    private Date date;

    private Date dateTime;

    public TestForm() {
    }

    public String getTextField() {
        return textField;
    }

    public void setTextField(final String textField) {
        this.textField = textField;
    }

    public String getTextArea() {
        return textArea;
    }

    public void setTextArea(final String textArea) {
        this.textArea = textArea;
    }

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(final Boolean checkbox) {
        this.checkbox = checkbox;
    }

    public Brand getEntitySelect() {
        return entitySelect;
    }

    public void setEntitySelect(final Brand entitySelect) {
        this.entitySelect = entitySelect;
    }

    public List<Role> getEntityMultiSelect() {
        return entityMultiSelect;
    }

    public void setEntityMultiSelect(final List<Role> multiSelect) {
        this.entityMultiSelect = multiSelect;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(final Date dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getCheckboxPicker() {
        return checkboxPicker;
    }

    public void setCheckboxPicker(final Boolean checkboxPicker) {
        this.checkboxPicker = checkboxPicker;
    }

    public Boolean getCheckboxToggle() {
        return checkboxToggle;
    }

    public void setCheckboxToggle(final Boolean checkboxToggle) {
        this.checkboxToggle = checkboxToggle;
    }

    public String getColorPicker() {
        return colorPicker;
    }

    public void setColorPicker(final String colorPicker) {
        this.colorPicker = colorPicker;
    }
}
