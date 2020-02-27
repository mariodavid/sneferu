package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.ColorPicker;
import de.diedavids.sneferu.components.testapi.GenericComponentTestAPI;

public class ColorPickerComponentDescriptor
        extends GenericComponentDescriptor<ColorPicker, GenericComponentTestAPI<ColorPicker>> {

    public ColorPickerComponentDescriptor(String componentId) {
        super(ColorPicker.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<ColorPicker> createTestAPI(ColorPicker component) {
        return new GenericComponentTestAPI<>(component);
    }
}
