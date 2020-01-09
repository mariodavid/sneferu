package com.haulmont.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.TreeDataGrid;
import com.haulmont.sneferu.components.testapi.GenericComponentTestAPI;

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
