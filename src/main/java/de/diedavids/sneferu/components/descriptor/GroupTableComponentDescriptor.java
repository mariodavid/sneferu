package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.GroupTable;
import de.diedavids.sneferu.components.testapi.GenericComponentTestAPI;

public class GroupTableComponentDescriptor
        extends GenericComponentDescriptor<GroupTable, GenericComponentTestAPI<GroupTable>> {

    public GroupTableComponentDescriptor(String componentId) {
        super(GroupTable.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<GroupTable> createTestAPI(GroupTable component) {
        return new GenericComponentTestAPI<>(component);
    }
}
