package com.haulmont.sneferu.interactions;

import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.sneferu.screen.StandardEditorTestAPI;
import com.haulmont.sneferu.InteractionWithOutcome;

public class EntityValueInteraction<T> implements InteractionWithOutcome<T, StandardEditorTestAPI> {

    private final String attribute;

    public EntityValueInteraction(String attribute, Class<T> type) {
        this.attribute = attribute;
    }

    @Override
    public T execute(StandardEditorTestAPI screenTestAPI) {
        return ((StandardEditor) screenTestAPI.screen()).getEditedEntity().<T>getValue(attribute);
    }

}
