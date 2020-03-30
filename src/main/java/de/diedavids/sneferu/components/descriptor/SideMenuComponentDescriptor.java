package de.diedavids.sneferu.components.descriptor;

import com.haulmont.cuba.gui.components.mainwindow.SideMenu;
import de.diedavids.sneferu.components.testapi.GenericComponentTestAPI;

public class SideMenuComponentDescriptor extends GenericComponentDescriptor<SideMenu, GenericComponentTestAPI<SideMenu>> {

    public SideMenuComponentDescriptor(String componentId) {
        super(SideMenu.class, componentId);
    }

    @Override
    public GenericComponentTestAPI createTestAPI(SideMenu component) {
        return new GenericComponentTestAPI(component);
    }
}