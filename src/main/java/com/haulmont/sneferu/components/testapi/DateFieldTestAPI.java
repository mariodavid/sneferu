package com.haulmont.sneferu.components.testapi;

import com.haulmont.cuba.gui.components.DateField;

public class DateFieldTestAPI extends GenericComponentTestAPI<DateField> {

  public DateFieldTestAPI(DateField component) {
    super(component);
  }

  public DateFieldTestAPI enter(Object o) {
    rawComponent()
        .setValue(o);
    return this;
  }
}
