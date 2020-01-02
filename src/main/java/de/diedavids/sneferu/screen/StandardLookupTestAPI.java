package de.diedavids.sneferu.screen;

import com.haulmont.cuba.gui.screen.StandardLookup;

public class StandardLookupTestAPI<S extends StandardLookup> extends ScreenTestAPI<S, StandardLookupTestAPI> {

    public StandardLookupTestAPI(Class<S> lookupScreenClass, S screen) {
        super(lookupScreenClass, screen);
    }

}
