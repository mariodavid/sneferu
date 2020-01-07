package de.diedavids.sneferu;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.screen.StandardLookup;
import de.diedavids.sneferu.screen.InputDialogTestAPI;
import de.diedavids.sneferu.screen.ScreenTestAPI;
import de.diedavids.sneferu.screen.StandardEditorTestAPI;
import de.diedavids.sneferu.screen.StandardLookupTestAPI;


/**
 * UITestAPI is the main entry point for interacting with Screens in an web integration test case.
 *
 * This API provides the ability to receive / work with ScreenTestAPI instances, that allow
 * to interact with a particular screen via its Test API.
 */
public interface UiTestAPI {



  /**
   * retrieves the currently opened InputDialog (if any)
   *
   * @return instance of InputDialog (via InputDialogTestAPI)
   */
  InputDialogTestAPI openedInputDialog();

  /**
   * returns a Test API instance for a given Standard Editor Screen class if screen is opened.
   *
   * If screen type is not open, a ScreenNotOpenException will be thrown
   *
   * @param screenEditorClass the type of the Standard Editor Screen
   * @param <S> the type of the Screen
   *
   * @return an instance of the Test API
   */
  <S extends StandardEditor> StandardEditorTestAPI<S> getOpenedEditorScreen(
      Class<S> screenEditorClass
  );


  /**
   * returns a Test API instance for a given Standard Lookup Screen class if screen is opened.
   *
   * If screen type is not open, a ScreenNotOpenException will be thrown
   *
   * @param screenLookupClass the type of the Standard Lookup Screen
   * @param <S> the type of the Screen
   *
   * @return an instance of the Test API
   */
  <S extends StandardLookup> StandardLookupTestAPI<S> getOpenedLookupScreen(
      Class<S> screenLookupClass
  );


  /**
   * returns a Test API instance for a given Screen class if screen is opened.
   *
   * If screen type is not open, a ScreenNotOpenException will be thrown
   *
   * @param screenClass the type of the Screen
   * @param <S> the type of the Screen
   *
   * @return an instance of the Test API
   */
  <S extends Screen> ScreenTestAPI<S, ScreenTestAPI> getOpenedScreen(
      Class<S> screenClass
  );


  /**
   * opens a Standard Editor Screen
   * @param entityClass the entity class of the Editor
   * @param editorScreenClass the class of the Editor
   * @param <E> type of the Entity
   * @param <S> type of the Standard Editor
   * @return an instance of this editor screen (via StandardEditorTestAPI)
   */
  <E extends Entity, S extends StandardEditor> StandardEditorTestAPI<S> openStandardEditor(
      Class<E> entityClass,
      Class<S> standardEditorClass
  );

  /**
   * opens a Standard Editor Screen for a given Entity
   * @param entityClass the entity class of the Editor
   * @param editorScreenClass the class of the Editor
   * @param entity the Entity instance to use
   * @param <E> type of the Entity
   * @param <S> type of the Standard Editor
   * @return an instance of this editor screen (via StandardEditorTestAPI)
   */
  <E extends Entity, S extends StandardEditor> StandardEditorTestAPI<S> openStandardEditor(
      Class<E> entityClass,
      Class<S> standardEditorClass,
      E entity
  );



  /**
   * opens a Standard Lookup Screen
   * @param entityClass the entity class of the StandardLookup
   * @param lookupScreenClass the class of the StandardLookup
   * @param <E> type of the Entity
   * @param <S> type of the StandardLookup
   * @return an instance of this lookup screen (via StandardLookupTestAPI)
   */
  <E extends Entity, S extends StandardLookup> StandardLookupTestAPI<S> openStandardLookup(
      Class<E> entityClass,
      Class<S> lookupScreenClass
  );

  /**
   * checks if a given screen is active
   * @param screenTestAPI the screen (via its ScreenTestAPI)
   * @return true if active, otherwise false
   */
  boolean isActive(ScreenTestAPI screenTestAPI);


  /**
   * closes all screens in the UI
   */
  void closeAllScreens();

}
