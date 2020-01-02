package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.TextField;
import de.diedavids.sneferu.components.testapi.TextFieldTestAPI;

public class TextFieldComponentDescriptor
        extends GenericComponentDescriptor<TextField, TextFieldTestAPI> {

    public TextFieldComponentDescriptor(String componentId) {
        super(TextField.class, componentId);
    }

    @Override
    public TextFieldTestAPI createTestAPI(TextField component) {
        return new TextFieldTestAPI(component);
    }
}
