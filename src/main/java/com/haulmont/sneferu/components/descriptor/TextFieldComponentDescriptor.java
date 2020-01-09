package com.haulmont.sneferu.components.descriptor;

import com.haulmont.cuba.gui.components.TextInputField;
import com.haulmont.sneferu.components.testapi.TextFieldTestAPI;

public class TextFieldComponentDescriptor
        extends GenericComponentDescriptor<TextInputField, TextFieldTestAPI> {

    public TextFieldComponentDescriptor(String componentId) {
        super(TextInputField.class, componentId);
    }

    @Override
    public TextFieldTestAPI createTestAPI(TextInputField component) {
        return new TextFieldTestAPI(component);
    }
}
