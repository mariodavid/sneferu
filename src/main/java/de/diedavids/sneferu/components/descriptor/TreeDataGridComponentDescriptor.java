package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.TreeDataGrid;
import de.diedavids.sneferu.components.testapi.GenericComponentTestAPI;

public class TreeDataGridComponentDescriptor
            extends GenericComponentDescriptor<TreeDataGrid, GenericComponentTestAPI<TreeDataGrid>> {

    public TreeDataGridComponentDescriptor(String componentId) {
        super(TreeDataGrid.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<TreeDataGrid> createTestAPI(TreeDataGrid component) {
        return new GenericComponentTestAPI<>(component);
    }
}
