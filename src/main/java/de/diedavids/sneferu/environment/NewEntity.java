package de.diedavids.sneferu.environment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation for a JUnit test case parameter.
 *
 * Using this Annotation, a new entity instance of the given type will be instantiated
 * and injected as a parameter into the test case.
 *
 * Example usage:
 *
 *<pre>{@code
 * @Test
 * public void myJunit5Test(@NewEntity Pet pikachu) {
 *   pikachu.setName("Pikachu")
 * }
 *}</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface NewEntity {

}
