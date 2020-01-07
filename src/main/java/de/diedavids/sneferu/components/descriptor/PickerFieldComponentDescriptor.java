package de.diedavids.sneferu.components.descriptor;

import com.haulmont.cuba.gui.components.PickerField;
import de.diedavids.sneferu.components.testapi.PickerFieldTestAPI;

public class PickerFieldComponentDescriptor
        extends GenericComponentDescriptor<PickerField, PickerFieldTestAPI> {

    public PickerFieldComponentDescriptor(String componentId) {
        super(PickerField.class, componentId);
    }

    @Override
    public PickerFieldTestAPI createTestAPI(PickerField component) {
        return new PickerFieldTestAPI(component);
    }
}
