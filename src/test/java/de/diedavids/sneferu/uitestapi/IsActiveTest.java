package de.diedavids.sneferu.uitestapi;

import static de.diedavids.sneferu.uitestapi.OpenScreensStub.activeScreens;
import static de.diedavids.sneferu.uitestapi.OpenScreensStub.noOpenScreens;
import static de.diedavids.sneferu.uitestapi.OpenScreensStub.openScreens;
import static de.diedavids.sneferu.uitestapi.OpenScreensStub.openedScreensStub;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.Screens.OpenedScreens;
import com.haulmont.cuba.web.app.main.MainScreen;
import com.haulmont.cuba.web.testsupport.TestUiEnvironment;
import de.diedavids.sneferu.CubaWebUiTestAPI;
import de.diedavids.sneferu.UiTestAPI;
import de.diedavids.sneferu.example.Customer;
import de.diedavids.sneferu.example.CustomerStandardEditor;
import de.diedavids.sneferu.screen.StandardEditorTestAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IsActiveTest {

  private UiTestAPI sut;
  private OpenedScreens openedScreens;

  private CustomerStandardEditor editor;

  @BeforeEach
  void setUp() {
    TestUiEnvironment environment = mock(TestUiEnvironment.class);
    openedScreens = openedScreensStub(environment);

    final ScreenBuilders screenBuilders = mock(ScreenBuilders.class);
    sut = new CubaWebUiTestAPI(
        environment,
        screenBuilders,
        MainScreen.class
    );

    editor = new CustomerStandardEditor();
  }


  @Test
  void given_screenIsOpen_expect_isActiveIsTrue() {

    // given:
    activeScreens(openedScreens)
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
    activeScreens(openedScreens)
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
    return (StandardEditorTestAPI<Customer, CustomerStandardEditor>) new StandardEditorTestAPI(
        Customer.class, foundCustomerEditor);
  }
}

