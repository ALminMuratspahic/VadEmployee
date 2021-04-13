package com.VaadinEmployees.common;

import com.vaadin.flow.component.textfield.NumberField;

public class CustomNumberField extends NumberField {

    public CustomNumberField(String label) {
        super(label);
        setMin(0);
    }

    @Override
    public Double getEmptyValue() {
        return this.getMin();
    }
}