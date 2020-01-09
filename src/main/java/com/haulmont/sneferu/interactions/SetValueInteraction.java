package com.haulmont.sneferu.interactions;

import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.sneferu.Interaction;
import com.haulmont.sneferu.components.ComponentDescriptor;
import com.haulmont.sneferu.components.testapi.ComponentTestAPI;
import com.haulmont.sneferu.screen.ScreenTestAPI;

public class SetValueInteraction implements Interaction {

    private final ComponentDescriptor<? extends Component, ? extends ComponentTestAPI> componentDescriptor;
    private final Object value;

    public <C extends Component, F extends ComponentTestAPI<C>> SetValueInteraction(
            ComponentDescriptor<C, F> componentDescriptor,
            Object value
    ) {
        this.componentDescriptor = componentDescriptor;
        this.value = value;
    }



    @Override
    public void execute(ScreenTestAPI screenTestAPI) {
        HasValue hasValue = (HasValue) screenTestAPI.component(componentDescriptor).rawComponent();
        hasValue.setValue(value);
    }


}
