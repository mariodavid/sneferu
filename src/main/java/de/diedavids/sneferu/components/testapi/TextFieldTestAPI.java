package de.diedavids.sneferu.components.testapi;

import com.haulmont.cuba.gui.components.TextField;


public class TextFieldTestAPI extends GenericComponentTestAPI<TextField> {

    public TextFieldTestAPI(TextField component) {
        super(component);
    }

    public TextFieldTestAPI enter(Object o) {
        return this;
    }
}
