package de.diedavids.sneferu.interactions;

import com.haulmont.cuba.gui.screen.CloseAction;
import com.haulmont.cuba.gui.util.OperationResult;
import de.diedavids.sneferu.InteractionWithOutcome;
import de.diedavids.sneferu.screen.InputDialogTestAPI;
import java.util.Optional;

public class CloseInputDialogInteraction  implements InteractionWithOutcome<OperationResult, InputDialogTestAPI> {

    private final Optional<CloseAction> closeAction;

    public CloseInputDialogInteraction() {
        closeAction = Optional.empty();
    }

    public CloseInputDialogInteraction(CloseAction closeAction) {
        this.closeAction = Optional.ofNullable(closeAction);
    }

    @Override
    public OperationResult execute(InputDialogTestAPI screenTestAPI) {
        return closeAction
            .map(action -> screenTestAPI.screen().close(action))
            .orElseGet(() -> screenTestAPI.screen().closeWithDefaultAction());
    }

}