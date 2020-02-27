package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.CheckBox;
import de.diedavids.sneferu.components.testapi.GenericComponentTestAPI;

public class CheckBoxComponentDescriptor
        extends GenericComponentDescriptor<CheckBox, GenericComponentTestAPI<CheckBox>> {

    public CheckBoxComponentDescriptor(String componentId) {
        super(CheckBox.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<CheckBox> createTestAPI(CheckBox component) {
        return new GenericComponentTestAPI<>(component);
    }
}
