package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.Button;
import de.diedavids.sneferu.components.testapi.ButtonTestAPI;

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
