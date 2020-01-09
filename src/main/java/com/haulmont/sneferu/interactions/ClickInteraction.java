package com.haulmont.sneferu.interactions;

import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.sneferu.screen.ScreenTestAPI;
import com.haulmont.sneferu.Interaction;
import com.haulmont.sneferu.components.ComponentDescriptor;
import com.haulmont.sneferu.components.testapi.ComponentTestAPI;

public class ClickInteraction implements Interaction {

    private final ComponentDescriptor<? extends Component, ? extends ComponentTestAPI> componentDescriptor;
    private ScreenTestAPI screenTestAPI;

    public <C extends Component, F extends ComponentTestAPI<C>> ClickInteraction(
        ComponentDescriptor<C, F> componentDescriptor) {
        this.componentDescriptor = componentDescriptor;
    }

    @Override
    public void execute(ScreenTestAPI screenTestAPI) {
        this.screenTestAPI = screenTestAPI;
        doClick(componentDescriptor.getId());
    }

    protected void doClick(String componentId) {
        Button button = (Button) screenTestAPI.screen().getWindow().getComponentNN(componentId);
        clickButton(button);
    }

    protected void clickButton(Button button) {
        button.unwrap(com.vaadin.ui.Button.class).click();
    }

}
