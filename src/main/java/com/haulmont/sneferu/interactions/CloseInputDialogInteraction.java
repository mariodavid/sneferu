package com.haulmont.sneferu.interactions;

import com.haulmont.cuba.gui.util.OperationResult;
import com.haulmont.sneferu.screen.InputDialogTestAPI;
import com.haulmont.sneferu.InteractionWithOutcome;

public class CloseInputDialogInteraction  implements InteractionWithOutcome<OperationResult, InputDialogTestAPI> {

    @Override
    public OperationResult execute(InputDialogTestAPI screenTestAPI) {
        return screenTestAPI.screen().closeWithDefaultAction();
    }

}