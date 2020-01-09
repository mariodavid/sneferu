package com.haulmont.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.sneferu.components.testapi.GenericComponentTestAPI;

public class DataGridComponentDescriptor
            extends GenericComponentDescriptor<DataGrid, GenericComponentTestAPI<DataGrid>> {

    public DataGridComponentDescriptor(String componentId) {
        super(DataGrid.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<DataGrid> createTestAPI(DataGrid component) {
        return new GenericComponentTestAPI<>(component);
    }
}
