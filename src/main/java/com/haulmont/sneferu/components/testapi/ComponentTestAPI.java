package com.haulmont.sneferu.components.testapi;

import com.haulmont.cuba.gui.components.Component;

public abstract class ComponentTestAPI<C extends Component> {

    public abstract C rawComponent();
}
