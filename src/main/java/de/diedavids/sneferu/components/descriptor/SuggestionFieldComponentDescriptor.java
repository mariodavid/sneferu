package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.SuggestionField;
import de.diedavids.sneferu.components.testapi.SuggestionFieldTestAPI;

public class SuggestionFieldComponentDescriptor
        extends GenericComponentDescriptor<SuggestionField, SuggestionFieldTestAPI> {

    public SuggestionFieldComponentDescriptor(String componentId) {
        super(SuggestionField.class, componentId);
    }

    @Override
    public SuggestionFieldTestAPI createTestAPI(SuggestionField component) {
        return new SuggestionFieldTestAPI(component);
    }
}
