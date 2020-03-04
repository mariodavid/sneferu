package de.diedavids.sneferu.uitestapi;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.Screens.OpenedScreens;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.web.testsupport.TestUiEnvironment;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.mockito.stubbing.OngoingStubbing;

class OpenScreensStub {

  public static OpenedScreens openedScreensStub(TestUiEnvironment environment) {

    Screens screens = mock(Screens.class);
    when(environment.getScreens()).thenReturn(screens);

    OpenedScreens openedScreens = mock(OpenedScreens.class);
    when(screens.getOpenedScreens()).thenReturn(openedScreens);

    return openedScreens;
  }
  public static OngoingStubbing<Collection<Screen>> activeScreens(OpenedScreens openedScreens) {
    return when(openedScreens.getActiveScreens());
  }

  public static List<Screen> openScreens(Screen screen) {
    return Collections.singletonList(
        screen
    );
  }

  public static List<Screen> noOpenScreens() {
    return Collections.emptyList();
  }
}

