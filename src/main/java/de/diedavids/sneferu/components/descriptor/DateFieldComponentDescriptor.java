package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.DateField;
import de.diedavids.sneferu.components.testapi.DateFieldTestAPI;

public class DateFieldComponentDescriptor
        extends GenericComponentDescriptor<DateField, DateFieldTestAPI> {

    public DateFieldComponentDescriptor(String componentId) {
        super(DateField.class, componentId);
    }

    @Override
    public DateFieldTestAPI createTestAPI(DateField component) {
        return new DateFieldTestAPI(component);
    }
}
