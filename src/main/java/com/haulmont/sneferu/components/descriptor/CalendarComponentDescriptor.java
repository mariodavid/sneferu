package com.haulmont.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.sneferu.components.testapi.GenericComponentTestAPI;

public class CalendarComponentDescriptor
            extends GenericComponentDescriptor<Calendar, GenericComponentTestAPI<Calendar>> {

    public CalendarComponentDescriptor(String componentId) {
        super(Calendar.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<Calendar> createTestAPI(Calendar component) {
        return new GenericComponentTestAPI<>(component);
    }
}
