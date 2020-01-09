package com.haulmont.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.Button;
import com.haulmont.sneferu.components.testapi.ButtonTestAPI;

public class ButtonComponentDescriptor
        extends GenericComponentDescriptor<Button, ButtonTestAPI> {

    public ButtonComponentDescriptor(String componentId) {
        super(Button.class, componentId);
    }

    @Override
    public ButtonTestAPI createTestAPI(Button component) {
        return new ButtonTestAPI(component);
    }
}
