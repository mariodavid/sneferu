package de.diedavids.sneferu.components.descriptor;

import com.haulmont.cuba.gui.components.TextInputField;
import de.diedavids.sneferu.components.testapi.TextInputFieldTestAPI;

public class TextInputFieldComponentDescriptor
        extends GenericComponentDescriptor<TextInputField, TextInputFieldTestAPI> {

    public TextInputFieldComponentDescriptor(String componentId) {
        super(TextInputField.class, componentId);
    }

    @Override
    public TextInputFieldTestAPI createTestAPI(TextInputField component) {
        return new TextInputFieldTestAPI(component);
    }
}
