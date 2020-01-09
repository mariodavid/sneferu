package com.haulmont.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.Accordion;
import com.haulmont.sneferu.components.testapi.GenericComponentTestAPI;

public class AccordionComponentDescriptor
        extends GenericComponentDescriptor<Accordion, GenericComponentTestAPI<Accordion>> {

    public AccordionComponentDescriptor(String componentId) {
        super(Accordion.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<Accordion> createTestAPI(Accordion component) {
        return new GenericComponentTestAPI<>(component);
    }
}
