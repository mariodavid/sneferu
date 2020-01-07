package de.diedavids.sneferu.components.descriptor;

import com.haulmont.cuba.gui.components.LookupField;
import de.diedavids.sneferu.components.testapi.LookupFieldTestAPI;

public class LookupFieldComponentDescriptor
        extends GenericComponentDescriptor<LookupField, LookupFieldTestAPI> {

    public LookupFieldComponentDescriptor(String componentId) {
        super(LookupField.class, componentId);
    }

    @Override
    public LookupFieldTestAPI createTestAPI(LookupField component) {
        return new LookupFieldTestAPI(component);
    }
}
