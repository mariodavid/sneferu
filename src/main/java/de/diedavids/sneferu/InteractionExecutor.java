package de.diedavids.sneferu;

import de.diedavids.sneferu.screen.ScreenTestAPI;

/**
 * executes a given Interaction in a fluent API manner.
 *
 * The type <THIS> refers to the implementation type itself.
 * It is used to return the correct type in the fluent API
 *
 * @param <THIS> the type of the class implementing this interface
 */
public interface InteractionExecutor<THIS> {


  /**
   * starts the interactions
   *
   * @param interaction the interactions to execute
   * @return <THIS> the interactions executor for further method chaining
   */
  THIS interact(Interaction interaction);

  /**
   * continues the interactions (alias for interact())
   *
   * @param interaction the interactions to execute
   * @return <THIS> the interactions executor for further method chaining
   */
  THIS andThen(Interaction interaction);


  <O, T extends ScreenTestAPI> O verify(InteractionWithOutcome<O, T> interaction);

  <O, T extends ScreenTestAPI> O get(InteractionWithOutcome<O, T> interaction);
  <O, T extends ScreenTestAPI> O interactAndGet(InteractionWithOutcome<O, T> interaction);
  <O, T extends ScreenTestAPI> O andThenGet(InteractionWithOutcome<O, T> interaction);

  <O, T extends ScreenTestAPI> O andThenVerify(InteractionWithOutcome<O, T> interaction);

}
