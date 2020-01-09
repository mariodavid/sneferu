package com.haulmont.sneferu.interactions;

import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.util.OperationResult;
import com.haulmont.sneferu.screen.StandardEditorTestAPI;
import com.haulmont.sneferu.InteractionWithOutcome;

public class CloseEditorInteraction implements InteractionWithOutcome<OperationResult, StandardEditorTestAPI> {

    @Override
    public OperationResult execute(StandardEditorTestAPI screenTestAPI) {
        return ((StandardEditor) screenTestAPI.screen()).closeWithCommit();
    }

}
