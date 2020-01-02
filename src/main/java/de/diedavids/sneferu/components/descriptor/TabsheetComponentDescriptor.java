package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.TabSheet;
import de.diedavids.sneferu.components.testapi.GenericComponentTestAPI;

public class TabsheetComponentDescriptor
        extends GenericComponentDescriptor<TabSheet, GenericComponentTestAPI<TabSheet>> {

    public TabsheetComponentDescriptor(String componentId) {
        super(TabSheet.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<TabSheet> createTestAPI(TabSheet component) {
        return new GenericComponentTestAPI<>(component);
    }
}
