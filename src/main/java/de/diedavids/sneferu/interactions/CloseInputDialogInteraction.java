package de.diedavids.sneferu.interactions;

import com.haulmont.cuba.gui.util.OperationResult;
import de.diedavids.sneferu.screen.InputDialogTestAPI;
import de.diedavids.sneferu.InteractionWithOutcome;

public class CloseInputDialogInteraction  implements InteractionWithOutcome<OperationResult, InputDialogTestAPI> {

    @Override
    public OperationResult execute(InputDialogTestAPI screenTestAPI) {
        return screenTestAPI.screen().closeWithDefaultAction();
    }

}