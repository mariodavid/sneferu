package de.diedavids.sneferu.components.testapi;

import com.haulmont.cuba.gui.components.SuggestionField;

public class SuggestionFieldTestAPI extends GenericComponentTestAPI<SuggestionField> {
    public SuggestionFieldTestAPI(SuggestionField component) {
        super(component);
    }

    public SuggestionFieldTestAPI search(Object searchTerm) {


        rawComponent().setValue(searchTerm);

        return this;
    }
}
