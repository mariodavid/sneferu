package de.diedavids.sneferu;

import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.screen.EditorScreen;
import de.diedavids.sneferu.components.ComponentDescriptor;
import de.diedavids.sneferu.components.descriptor.TabsheetComponentDescriptor;
import de.diedavids.sneferu.components.testapi.ComponentTestAPI;
import de.diedavids.sneferu.interactions.*;

/**
 * Factory methods for common interactions
 */
public class Interactions {


    /**
     * creates a set value interactions, that sets a value on a given component
     *
     * @param componentDescriptor the component descriptor describing the component to act upon
     * @param value the value that should be set
     * @param <C> type of the Component
     * @param <F> type of the Component Test API
     *
     * @return a SetValueInteraction instance
     */
    public static <C extends Component, F extends ComponentTestAPI<C>> SetValueInteraction setValue(
            ComponentDescriptor<C, F> componentDescriptor,
            Object value
    ) {
        return new SetValueInteraction(componentDescriptor, value);
    }

    /**
     * alias for setValue interactions
     *
     * creates a set value interactions, that sets a value on a given component
     *
     * @param componentDescriptor the component descriptor describing the component to act upon
     * @param value the value that should be set
     * @param <C> type of the Component
     * @param <F> type of the Component Test API
     *
     * @return a SetValueInteraction instance
     */
    public static <C extends Component, F extends ComponentTestAPI<C>> SetValueInteraction select(
            ComponentDescriptor<C, F> componentDescriptor,
            Object value
    ) {
        return setValue(componentDescriptor, value);
    }



    /**
     * alias for setValue interactions
     *
     * creates a set value interactions, that sets a value on a given component
     *
     * @param componentDescriptor the component descriptor describing the component to act upon
     * @param value the value that should be set
     * @param <C> type of the Component
     * @param <F> type of the Component Test API
     *
     * @return a SetValueInteraction instance
     */
    public static <C extends Component, F extends ComponentTestAPI<C>> SetValueInteraction enter(
            ComponentDescriptor<C, F> componentDescriptor,
            Object value
    ) {
        return setValue(componentDescriptor, value);
    }



    /**
     * creates a open tab interactions, that opens a tab for a given TabSheet component
     *
     * @param tabSheet the tabSheet component descriptor describing the component to act upon
     * @param tabId the tab that should be opened
     *
     * @return a OpenTabInteraction instance
     */
    public static OpenTabInteraction openTab(TabsheetComponentDescriptor tabSheet, String tabId) {
        return new OpenTabInteraction(tabSheet, tabId);
    }

    public static <T extends Object> EditorValueInteraction<T> editorValue(String attribute, Class<T> type) {
        return new EditorValueInteraction<>(attribute, type);
    }

    /**
     * creates a Close Editor interactions, that closes an editor with the Close and Commit Close Action {@link EditorScreen#WINDOW_COMMIT_AND_CLOSE}
     *
     * @return a CloseEditorInteraction instance
     */
    public static CloseEditorInteraction closeEditor() {
        return new CloseEditorInteraction();
    }


    public static CloseInputDialogInteraction closeInputDialog() {
        return new CloseInputDialogInteraction();
    }


    public static CancelEditorInteraction cancelEditor() {
        return new CancelEditorInteraction();
    }


    public static ScreenOpenModeInteraction screenOpenMode() {
        return new ScreenOpenModeInteraction();
    }


    /**
     * creates a Click interactions, that clicks on a given component
     *
     * @param componentDescriptor the component to click on
     * @param <C> type of the Component
     * @param <F> type of the Component Test API
     *
     * @return a ClickInstance instance
     */
    public static <C extends Component, F extends ComponentTestAPI<C>> ClickInteraction click(ComponentDescriptor<C, F> componentDescriptor) {
        return new ClickInteraction(componentDescriptor);
    }


}
