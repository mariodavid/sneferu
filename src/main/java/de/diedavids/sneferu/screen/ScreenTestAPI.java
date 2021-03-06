package de.diedavids.sneferu.screen;

import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.screen.Screen;
import de.diedavids.sneferu.Interaction;
import de.diedavids.sneferu.InteractionExecutor;
import de.diedavids.sneferu.InteractionWithOutcome;
import de.diedavids.sneferu.components.ComponentDescriptor;
import de.diedavids.sneferu.components.testapi.ComponentTestAPI;
import java.util.function.Supplier;

public abstract class ScreenTestAPI<S extends Screen, THIS extends ScreenTestAPI> implements
    InteractionExecutor<THIS> {

  protected S screen;
  protected Supplier<S> screenSupplier;
  protected Class<S> screenClass;


  /**
   * access to the underlying screen instance
   *
   * Useful when need some direct method invocation / data from the screen instance that is not
   * expressed through the corresponding ScreenTestAPI.
   *
   * @return the screen instance
   */
  public S screen() {
    if (screenSupplier != null && screen == null) {
      initScreen();
    }
    return screen;
  }

  private void initScreen() {
    this.screen = screenSupplier.get();
  }


  public ScreenTestAPI(Class<S> screenClass, S screen) {
    this.screenClass = screenClass;
    this.screen = screen;
  }


  public ScreenTestAPI(Class<S> screenClass, Supplier<S> screenSupplier) {
    this.screenClass = screenClass;
    this.screenSupplier = screenSupplier;
  }

  public <C extends Component, F extends ComponentTestAPI<C>> F component(
      ComponentDescriptor<C, F> componentDescriptor) {
    return componentDescriptor.createTestAPI(rawComponent(componentDescriptor));
  }


  public <C extends Component, F extends ComponentTestAPI<C>> C rawComponent(
      ComponentDescriptor<C, F> componentDescriptor) {
    return (C) screen()
        .getWindow()
        .getComponentNN(componentDescriptor.getId());
  }


  @Override
  public THIS interact(Interaction interaction) {
    doInteract(interaction);
    return (THIS) this;
  }

  @Override
  public THIS andThen(Interaction interaction) {
    doInteract(interaction);
    return (THIS) this;
  }

  @Override
  public <O, T extends ScreenTestAPI> O verify(InteractionWithOutcome<O, T> interaction) {
    return doGet(interaction);
  }


  @Override
  public <O, THIS extends ScreenTestAPI> O get(InteractionWithOutcome<O, THIS> interaction) {
    return doGet(interaction);
  }


  @Override
  public <O, THIS extends ScreenTestAPI> O interactAndGet(
      InteractionWithOutcome<O, THIS> interaction) {
    return doGet(interaction);
  }

  @Override
  public <O, THIS extends ScreenTestAPI> O andThenGet(InteractionWithOutcome<O, THIS> interaction) {
    return doGet(interaction);
  }

  @Override
  public <O, T extends ScreenTestAPI> O andThenVerify(InteractionWithOutcome<O, T> interaction) {
    return doGet(interaction);
  }

  private void doInteract(Interaction interaction) {
    interaction.execute(this);
  }

  private <O, THIS extends ScreenTestAPI> O doGet(InteractionWithOutcome<O, THIS> interaction) {
    return interaction.execute((THIS) this);
  }


}

