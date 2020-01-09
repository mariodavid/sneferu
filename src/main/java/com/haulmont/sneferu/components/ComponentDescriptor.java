package com.haulmont.sneferu.components;

import com.haulmont.cuba.gui.components.Component;
import com.haulmont.sneferu.components.testapi.ComponentTestAPI;

public interface ComponentDescriptor<C extends Component, F extends ComponentTestAPI<C>> {

    String getId();

    F createTestAPI(C component);
}
