package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.Table;
import de.diedavids.sneferu.components.testapi.GenericComponentTestAPI;

public class TableComponentDescriptor
        extends GenericComponentDescriptor<Table, GenericComponentTestAPI<Table>> {

    public TableComponentDescriptor(String componentId) {
        super(Table.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<Table> createTestAPI(Table component) {
        return new GenericComponentTestAPI<>(component);
    }
}
