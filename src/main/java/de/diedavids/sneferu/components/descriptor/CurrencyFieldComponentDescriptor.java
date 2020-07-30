package de.diedavids.sneferu.components.descriptor;

import com.haulmont.cuba.gui.components.CurrencyField;
import de.diedavids.sneferu.components.testapi.CurrencyFieldTestAPI;

public class CurrencyFieldComponentDescriptor
        extends GenericComponentDescriptor<CurrencyField, CurrencyFieldTestAPI> {

    public CurrencyFieldComponentDescriptor(String componentId) {
        super(CurrencyField.class, componentId);
    }

    @Override
    public CurrencyFieldTestAPI createTestAPI(CurrencyField component) {
        return new CurrencyFieldTestAPI(component);
    }
}