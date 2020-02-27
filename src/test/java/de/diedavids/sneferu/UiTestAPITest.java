package de.diedavids.sneferu;

import static de.diedavids.sneferu.ComponentDescriptors.button;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.Screens.OpenedScreens;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.web.app.main.MainScreen;
import com.haulmont.cuba.web.testsupport.TestUiEnvironment;
import de.diedavids.sneferu.example.Customer;
import de.diedavids.sneferu.example.CustomerStandardEditor;
import de.diedavids.sneferu.screen.StandardEditorTestAPI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

class UiTestAPITest {

  private UiTestAPI sut;
  private OpenedScreens openedScreens;

  @BeforeEach
  void setUp() {
    TestUiEnvironment environment = mock(TestUiEnvironment.class);

    Screens screens = mock(Screens.class);
    when(environment.getScreens()).thenReturn(screens);

    openedScreens = mock(OpenedScreens.class);
    when(screens.getOpenedScreens()).thenReturn(openedScreens);

    final ScreenBuilders screenBuilders = mock(ScreenBuilders.class);
    sut = new CubaWebUiTestAPI(
        environment,
        screenBuilders,
        MainScreen.class
    );
  }

  @Test
  void given_screenIsNotOpen_when_aLazyOpenedStandardEditor_anInstanceIsReturnedThatTriesToFetchTheScreenOnTheFly_whenRequestingScreen() {

    // given:
    final CustomerStandardEditor foundCustomerEditor = new CustomerStandardEditor();

    allScreens()
        .thenReturn(
            noOpenScreens()
        )
        .thenReturn(
            openScreens(foundCustomerEditor)
        );

    // expect:
    assertThat(lazyOpenedEditorScreen().screen())
        .isEqualTo(foundCustomerEditor);
  }

  @Test
  void given_screenIsNotOpen_when_aLazyOpenedStandardEditor_anInstanceIsReturnedThatTriesToFetchTheScreenOnTheFly_whenRequestingComponent() {

    // given:
    final CustomerStandardEditor foundCustomerEditor = mock(CustomerStandardEditor.class);

    // and:
    final Window screenWindow = mock(Window.class);
    when(foundCustomerEditor.getWindow())
        .thenReturn(screenWindow);

    // and:
    final Button okBtn = mock(Button.class);
    when(screenWindow.getComponentNN("okBtn"))
        .thenReturn(okBtn);

    allScreens()
        .thenReturn(
            noOpenScreens()
        )
        .thenReturn(
            openScreens(foundCustomerEditor)
        );

    // expect:
    assertThat(lazyOpenedEditorScreen().rawComponent(button("okBtn")))
        .isEqualTo(okBtn);
  }

  private StandardEditorTestAPI<Customer, CustomerStandardEditor> lazyOpenedEditorScreen() {
    return sut
        .getLazyOpenedEditorScreen(CustomerStandardEditor.class);
  }


  @Test
  void given_screenIsOpen_when_aLazyOpenedStandardEditor_anInstanceIsReturned() {

    // given:
    final CustomerStandardEditor foundCustomerEditor = new CustomerStandardEditor();

    allScreens()
        .thenReturn(
            openScreens(foundCustomerEditor)
        );

    // expect:
    assertThat(lazyOpenedEditorScreen().screen())
        .isEqualTo(foundCustomerEditor);
  }

  private OngoingStubbing<Collection<Screen>> allScreens() {
    return when(openedScreens.getAll());
  }

  @Nested
  class IsActive {

    private CustomerStandardEditor editor;

    @BeforeEach
    void setUp() {
      editor = new CustomerStandardEditor();
    }

    @Test
    void given_screenIsOpen_expect_isActiveIsTrue() {

      // given:
      activeScreens()
          .thenReturn(
              openScreens(editor)
          );

      // expect:
      assertThat(
          sut.isActive(testAPI(editor))
      ).isTrue();

    }
    @Test
    void given_screenIsNotOpen_expect_isActiveIsFalse() {

      // given:
      activeScreens()
          .thenReturn(
              noOpenScreens()
          );

      // expect:
      assertThat(
          sut.isActive(testAPI(editor))
      ).isFalse();

    }

    private StandardEditorTestAPI<Customer, CustomerStandardEditor> testAPI(
        CustomerStandardEditor foundCustomerEditor) {
      return (StandardEditorTestAPI<Customer, CustomerStandardEditor>) new StandardEditorTestAPI(Customer.class, foundCustomerEditor);
    }

  }

  private OngoingStubbing<Collection<Screen>> activeScreens() {
    return when(openedScreens.getActiveScreens());
  }

  private List<Screen> openScreens(CustomerStandardEditor foundCustomerEditor) {
    return Collections.singletonList(
        foundCustomerEditor
    );
  }

  private List<Screen> noOpenScreens() {
    return Collections.emptyList();
  }
}

