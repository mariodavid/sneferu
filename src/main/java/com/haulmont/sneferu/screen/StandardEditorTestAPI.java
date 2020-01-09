package com.haulmont.sneferu.screen;

import com.haulmont.cuba.gui.screen.StandardEditor;

public class StandardEditorTestAPI<S extends StandardEditor> extends ScreenTestAPI<S, StandardEditorTestAPI> {

    public StandardEditorTestAPI(Class<S> editorScreenClass, S screen) {
        super(editorScreenClass, screen);
    }

}
