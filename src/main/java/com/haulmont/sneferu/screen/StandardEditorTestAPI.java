package com.haulmont.sneferu.screen;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.screen.StandardEditor;
import java.util.function.Supplier;

public class StandardEditorTestAPI<E extends Entity, S extends StandardEditor<E>> extends ScreenTestAPI<S, StandardEditorTestAPI> {

    public StandardEditorTestAPI(Class<S> editorScreenClass, S screen) {
        super(editorScreenClass, screen);
    }

    public StandardEditorTestAPI(Class<S> editorScreenClass, Supplier<S> screenSupplier) {
        super(editorScreenClass, screenSupplier);
    }

}
