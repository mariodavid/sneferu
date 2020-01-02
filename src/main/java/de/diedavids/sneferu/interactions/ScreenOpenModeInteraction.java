package de.diedavids.sneferu.interactions;

import com.haulmont.cuba.gui.Screens;
import de.diedavids.sneferu.InteractionWithOutcome;
import de.diedavids.sneferu.screen.ScreenTestAPI;

public class ScreenOpenModeInteraction implements InteractionWithOutcome<Screens.LaunchMode, ScreenTestAPI> {
    @Override
    public Screens.LaunchMode execute(ScreenTestAPI screenTestAPI) {
        return screenTestAPI.screen().getWindow().getContext().getLaunchMode();
    }
}
