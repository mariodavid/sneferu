package de.diedavids.sneferu.interactions;

import com.haulmont.cuba.gui.screen.StandardEditor;
import de.diedavids.sneferu.InteractionWithOutcome;
import de.diedavids.sneferu.screen.StandardEditorTestAPI;

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
