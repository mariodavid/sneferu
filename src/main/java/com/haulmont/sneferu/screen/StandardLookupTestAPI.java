package com.haulmont.sneferu.screen;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.screen.StandardLookup;
import java.util.function.Supplier;

public class StandardLookupTestAPI<E extends Entity, S extends StandardLookup<E>> extends ScreenTestAPI<S, StandardLookupTestAPI> {

    public StandardLookupTestAPI(Class<S> lookupScreenClass, S screen) {
        super(lookupScreenClass, screen);
    }

    public StandardLookupTestAPI(Class<S> lookupScreenClass, Supplier<S> screenSuppier) {
        super(lookupScreenClass, screenSuppier);
    }



}
