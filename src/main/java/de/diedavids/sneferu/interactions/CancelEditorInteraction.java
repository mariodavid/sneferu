package de.diedavids.sneferu.interactions;

import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.util.OperationResult;
import de.diedavids.sneferu.InteractionWithOutcome;
import de.diedavids.sneferu.screen.StandardEditorTestAPI;

public class CancelEditorInteraction implements
    InteractionWithOutcome<OperationResult, StandardEditorTestAPI> {

    @Override
    public OperationResult execute(StandardEditorTestAPI screenTestAPI) {
        return ((StandardEditor) screenTestAPI.screen()).closeWithDiscard();
    }

}