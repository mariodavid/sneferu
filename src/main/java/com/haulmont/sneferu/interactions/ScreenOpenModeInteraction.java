package com.haulmont.sneferu.interactions;

import com.haulmont.cuba.gui.Screens;
import com.haulmont.sneferu.InteractionWithOutcome;
import com.haulmont.sneferu.screen.ScreenTestAPI;

public class ScreenOpenModeInteraction implements InteractionWithOutcome<Screens.LaunchMode, ScreenTestAPI> {
    @Override
    public Screens.LaunchMode execute(ScreenTestAPI screenTestAPI) {
        return screenTestAPI.screen().getWindow().getContext().getLaunchMode();
    }
}
