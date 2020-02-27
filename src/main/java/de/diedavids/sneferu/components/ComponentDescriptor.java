package de.diedavids.sneferu.components;

import com.haulmont.cuba.gui.components.Component;
import de.diedavids.sneferu.components.testapi.ComponentTestAPI;

public interface ComponentDescriptor<C extends Component, F extends ComponentTestAPI<C>> {

    String getId();

    F createTestAPI(C component);
}
