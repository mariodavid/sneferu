package com.haulmont.sneferu.interactions;

import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.sneferu.InteractionWithOutcome;
import com.haulmont.sneferu.components.ComponentDescriptor;
import com.haulmont.sneferu.components.testapi.ComponentTestAPI;

import com.haulmont.sneferu.screen.StandardEditorTestAPI;

public class GetValueInteraction<T> implements InteractionWithOutcome<T, StandardEditorTestAPI> {

  private final ComponentDescriptor<? extends Component, ? extends ComponentTestAPI> componentDescriptor;

  public <C extends Component, F extends ComponentTestAPI<C>> GetValueInteraction(
      ComponentDescriptor<C, F> componentDescriptor
  ) {
    this.componentDescriptor = componentDescriptor;
  }

  @Override
  public T execute(StandardEditorTestAPI screenTestAPI) {
    HasValue hasValue = (HasValue) screenTestAPI.component(componentDescriptor).rawComponent();
    return (T) hasValue.getValue();
  }
}