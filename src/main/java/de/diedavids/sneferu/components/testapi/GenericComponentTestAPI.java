package de.diedavids.sneferu.components.testapi;

import com.haulmont.cuba.gui.components.Component;

public class GenericComponentTestAPI<C extends Component> extends ComponentTestAPI<C> {
    private final C component;

    public GenericComponentTestAPI(C component) {
        this.component = component;
    }

    @Override
    public C rawComponent() {
        return component;
    }
}
