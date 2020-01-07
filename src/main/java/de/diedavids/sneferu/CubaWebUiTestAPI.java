package de.diedavids.sneferu;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.app.core.inputdialog.InputDialog;
import com.haulmont.cuba.gui.screen.*;

import com.haulmont.cuba.web.app.main.MainScreen;
import com.haulmont.cuba.web.testsupport.TestUiEnvironment;
import de.diedavids.sneferu.screen.InputDialogTestAPI;
import de.diedavids.sneferu.screen.ScreenTestAPI;
import de.diedavids.sneferu.screen.StandardEditorTestAPI;
import de.diedavids.sneferu.screen.StandardLookupTestAPI;
import java.util.ArrayList;

public class CubaWebUiTestAPI implements UiTestAPI {

    private final TestUiEnvironment environment;
    private final ScreenBuilders screenBuilders;
    private final Class<? extends MainScreen> mainScreenClass;

    public CubaWebUiTestAPI(
            TestUiEnvironment environment,
            ScreenBuilders screenBuilders,
            Class<? extends MainScreen> mainScreenClass
    ) {
        this.environment = environment;
        this.screenBuilders = screenBuilders;
        this.mainScreenClass = mainScreenClass;
    }

    private Screen rootScreen() {

        Screen rootScreenOrNull = getOpenedScreens().getRootScreenOrNull();

        if (rootScreenOrNull != null) {
            return rootScreenOrNull;
        }

        environment.getScreens()
                .create(mainScreenClass, OpenMode.ROOT)
                .show();

        return getOpenedScreens().getRootScreen();
    }



    @Override
    public <S extends StandardEditor> StandardEditorTestAPI<S> getOpenedEditorScreen(
        Class<S> screenEditorClass
    ) {
        Screen screen = getLastOpenedScreen();

        if (screen instanceof StandardEditor) {
            S castedScreen = (S) screen;
            return new StandardEditorTestAPI(screenEditorClass, castedScreen);
        }
        else {
            throw new ScreenNotOpenException();
        }
    }

    @Override
    public <S extends StandardLookup> StandardLookupTestAPI<S> getOpenedLookupScreen(
        Class<S> screenLookupClass
    ) {

        Screen screen = getLastOpenedScreen();

        if (screen instanceof StandardLookup) {
            S castedScreen = (S) screen;
            return new StandardLookupTestAPI<>(screenLookupClass, castedScreen);
        }
        else {
            throw new ScreenNotOpenException();
        }
    }

    @Override
    public <S extends Screen> ScreenTestAPI<S, ScreenTestAPI> getOpenedScreen(
        Class<S> screenClass
    ) {

        Screen screen = getLastOpenedScreen();

        if (screen != null) {
            return new ScreenTestAPI(screenClass, screen);
        }
        else {
            throw new ScreenNotOpenException();
        }
    }

    @Override
    public <E extends Entity, S extends StandardEditor> StandardEditorTestAPI<S> openStandardEditor(
        Class<E> entityClass,
        Class<S> standardEditorClass
    ) {
        S screen = (S) screenBuilders.editor(entityClass, rootScreen())
                .newEntity()
                .withScreenClass(standardEditorClass)
                .show();
        return new StandardEditorTestAPI<>(standardEditorClass, screen);
    }

    @Override
    public <E extends Entity, S extends StandardEditor> StandardEditorTestAPI<S> openStandardEditor(
        Class<E> entityClass, Class<S> standardEditorClass, E entity) {
        S screen = (S) screenBuilders.editor(entityClass, rootScreen())
            .editEntity(entity)
            .withScreenClass(standardEditorClass)
            .show();
        return new StandardEditorTestAPI<>(standardEditorClass, screen);
    }

    @Override
    public <E extends Entity, S extends StandardLookup> StandardLookupTestAPI<S> openStandardLookup(
        Class<E> entityClass,
        Class<S> lookupScreenClass
    ) {
        S screen = (S) screenBuilders.lookup(entityClass, rootScreen())
                .withScreenClass(lookupScreenClass)
                .show();
        return new StandardLookupTestAPI<>(lookupScreenClass, screen);
    }


    @Override
    public boolean isActive(ScreenTestAPI screenTestAPI) {

        return new ArrayList<>(getOpenedScreens()
                .getActiveScreens()).stream()
                .anyMatch(screen -> screenTestAPI.screen().getClass().isAssignableFrom(screen.getClass()));
    }

    private Screens.OpenedScreens getOpenedScreens() {
        return environment.getScreens()
                .getOpenedScreens();
    }

    private Screen getLastOpenedScreen() {
        ArrayList<Screen> activeScreens = new ArrayList<>(
            getOpenedScreens().getAll()
        );

        if (activeScreens.size() > 0) {
            return activeScreens.get(activeScreens.size() - 1);
        }
        else {
            return null;
        }
    }

    @Override
    public void closeAllScreens() {
        environment.getScreens().removeAll();
    }


    @Override
    public InputDialogTestAPI openedInputDialog() {
        return new ArrayList<>(getOpenedScreens()
                .getDialogScreens()).stream()
                .filter(screen -> InputDialog.class.isAssignableFrom(screen.getClass()))
                .findFirst()
                .map(screen -> new InputDialogTestAPI(InputDialog.class, (InputDialog) screen))
                .orElse(null);
    }
}
