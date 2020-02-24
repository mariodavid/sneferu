## Sneferu

Sneferu is a testing library to simplify web integration testing for a CUBA application.
It consists of a couple of APIs that allows the application developer to express interactions 
and verifications with UI screens via a fluent API. 


### Overview

Instead of spending too much time and money maintaining a selenium test suite, Sneferu is the way to have a very good test coverage and quality assurance at a fraction of the cost.

Using Sneferu it enables you to:
 
* verify any business logic in a Screen Controller
* ensure the correct linking between Screen XML and its Controller counterpart
* verify correct display of any programmatic creation of Screen Components / Dialogs
* emulate & verify data loading  
* emulate & verify Service Interface interactions

As Sneferu is based on the web integration test facilities of CUBA, there are certain limitation in what areas are not covered. In particular Sneferu relies on the abstractions provided by the CUBA and Vaadin component interfaces. It assumes that behind this interface everything "works as expected". 

What does Sneferu _not cover_:

* End-to-End data loading and displaying on a Screen
* End-to-End Service execution (middleware)
* Client-Side Vaadin UI logic that is executed only in the browser


If you can live with those trade-offs feel free to join the lovely world of web integration testing in CUBA expressed though a beautiful API.

#### Motivation & Background

Testing of a CUBA application on the web layer previously consists of two extremes:

1. write unit test for the business logic in the screen controllers
2. write a functional UI test that executes the application through the browser

Both of those extremes have their downsides.

The first one requires to mock out every programmatic interaction with the CUBA UI interfaces. Also unit test do not cover any of the screen layout definitions or the data binding of a Screen.

Selenium based UI testing on the other hand is much more black-box, slower, more brittle and overall harder to maintain. It can achieve a higher degree of confidence of correctness, as it exercises the application almost like the user does. But this trade off is a very expensive one. 

To mitigate that problem CUBA 7.1 introduced web integration testing.

Sneferu is taking that new addition and brings it to the next level by making it accessible and very easy to use.

The documentation shows all test cases written in Spock, but Sneferu works with different testing frameworks like JUnit as well.

An example test case in Sneferu looks like this:

```groovy
def "a customer can be created through a form starting from the customer browse screen"() {

    given:
    def customerBrowse = uiTestAPI
                            .openStandardLookup(Customer, CustomerBrowse)

    when:
    customerBrowse
            .interact(click(button("createBtn")))

    and:
    def customerEdit = uiTestAPI
                        .getOpenedEditorScreen(CustomerEdit)

    OperationResult result = customerEdit
            .interact(enter(textField("nameField"), "Bob Ross"))
            .andThenGet(closeEditor())

    then:
    result == OperationResult.success()

    and:
    uiTestAPI.isActive(customerBrowse)
    !uiTestAPI.isActive(customerEdit)
}
```

### Getting Started

In order to use Sneferu, it is required to add the dependency to the CUBA project. In the `build.gradle` the following dependency has to added to the web-module:

```groovy
configure(webModule) {
    // ...
    dependencies {
        testCompile('com.haulmont.sneferu:sneferu:**SNEFERU-VERSION**')
    }
}
```

Sneferu can be used with JUnit as well as Spock. Just add the corresponding dependencies into your CUBA project in order to use it with either of those frameworks.

Afterwards you can create your first web integration test:

```groovy
import com.haulmont.sneferu.UiTestAPI

import static com.haulmont.sneferu.ComponentDescriptors.*
import static com.haulmont.sneferu.Interactions.*


class FirstSneferuSpec extends Specification {

    @Shared
    @ClassRule
    TestUiEnvironment environment =
            new TestUiEnvironment(TestImprovementsWebTestContainer.Common.INSTANCE)
                    .withScreenPackages(
                            "com.haulmont.cuba.web.app.main",
                            "com.rtcab.ddceti.web.screens.customer"
                    )
                    .withUserLogin("admin")

    UiTestAPI uiTestAPI

    def setup() {
        uiTestAPI = new CubaWebUiTestAPI(
                environment, 
                AppBeans.get(ScreenBuilders.class), 
                MainScreen
        )
    }

    def "click a button, open a screen, enter some values and close the screen"() {

        given:
        def customerBrowse = uiTestAPI.openStandardLookup(Customer, CustomerBrowse)

        when:
        customerBrowse
                .interact(click(button("createBtn")))

        and:
        uiTestAPI.getOpenedEditorScreen(CustomerEdit)
                .interact(enter(textField("nameField"), "Bob Ross"))
                .andThenGet(closeEditor())

        then:
        uiTestAPI.isActive(customerBrowse)
    }
}
```

### Example Usage

A dedicated example of using Sneferu is shown via the CUBA Petclinic sample application: [cuba-platform/cuba-petclinic-using-sneferu](https://github.com/cuba-platform/cuba-petclinic-using-sneferu).

It contains a lot of example test cases that show the various usage options of Sneferu.


### Documentation

The different concepts when using Sneferu are described below. It mainly consists of the following:

1. `UiTestAPI`
2. `ScreenTestAPI` & `ScreenObject`
3. `ComponentTestAPI`
4. `Interactions`

Let's go through them one by one.

### UI Test API

The UI Test API is the interaction point in the test case that manages screens. It allows to open / close screens and retrieve information about opened screens.
 
An example of its usage looks like this:

```groovy
def "UI Test API usage"() {

    given: 'a screen can be opened by the test case'
    def customerBrowse = uiTestAPI
                            .openStandardLookup(Customer, CustomerBrowse)
                            
    and: 'a automatically opened screen can be retrieved'
    def customerEdit = uiTestAPI
                        .getOpenedEditorScreen(CustomerEdit)

    then: 'information about active state of a screen can be retrieved'
    uiTestAPI.isActive(customerEdit)
}
```

#### Creating a UiTestAPI

In order to use a UiTestAPI instance, it needs to be created. In its simplest form
an instance can be created by using the `CubaWebUiTestAPI` class directly:

```groovy
UiTestAPI uiTestAPI = new CubaWebUiTestAPI(
                environment, 
                AppBeans.get(ScreenBuilders.class), 
                MainScreen
        )
```

In order to function properly it requires an instance of the `TestUiEnvironment` as well as the class of the `MainScreen` used in the application.


### Screen Test API

The next concept of Sneferu allows to interact with a particular Screen and is called `ScreenTestAPI`. It consists of two main use-cases:

1. apply interactions (see [Interactions](#Interactions)) on a particular screen
2. retrieve components of a screen to interact with those through its [Component Test API](#Component-Test-API)

In order to interact with the `ScreenTestAPI` an instance of that must be retrieved via the `UiTestAPI` as described above. Once those instances are available (`customerBrowse` and `customerWithTabsEdit` in the following example), the API can be used to interact with a Screen via its Test API directly in the test case.

A usage of this looks like this:

```groovy
def "Screen Test API usage"() {

    when: 'interactions are applied for a particular screen'
    customerBrowse
             .interact(click(button("createBtn")))

    and: 'components can be retrieved for further reference'
    customerWithTabsEdit
             .component(dateField("birthdayField"))
             .enter(today)
}
```

More information on what Interactions are and how they can be used can be found in the corresponding [Interactions](#Interactions) section.

### Screen Objects

An extension of the ScreenTestAPI is the concept of a Screen Object.

Instead of using the API directly through the TestScreenAPI, it is also possible to create a [Screen Object](https://martinfowler.com/bliki/PageObject.html)
that represents the API of a particular Screen of the UI. This allows to create a dedicated abstraction between the test case and the screen that is under test.

##### Definition of a Screen Object (CustomerBrowseScreenObject)

In order to create a ScreenObject, a class needs to be created representing one screen (in this case `CustomerBrowse`). It furthermore needs to implement the interface `ScreenObject<T extends ScreenTestAPI>`. 

```java
public class CustomerBrowseScreenObject implements 
    ScreenObject<StandardLookupTestAPI<CustomerBrowse>> {

    private final UiTestAPI uiTestAPI;
    private StandardLookupTestAPI<CustomerBrowse> delegate;
    private final TestUiEnvironment testUiEnvironment;

    // ...

    public CustomerBrowseScreenObject searchForCustomer(Customer customer) {
        delegate
                .component(suggestionField("quickSearch"))
                .search(customer);

        return this;
    }

    public CustomerBrowseScreenObject searchForCustomer(String customerName) {

        Metadata metadata = testUiEnvironment.getContainer().getBean(Metadata.class);

        Customer customer = metadata.create(Customer.class);
        customer.setName(customerName);

        return searchForCustomer(customer);
    }

    public boolean isActive() {
        return uiTestAPI.isActive(delegate);
    }

}
```

With the above definition it is now possible to use this higher level abstraction in the different test cases:

##### Test Case with Screen Object

The shown test case is using the API of the ScreenObject, which consists of:

* `void searchForCustomer(String customerName)`
* `void searchForCustomer(Customer customer)`
* `boolean isActive()`

```groovy
def "screens can be used through its Screen Object Test API"() {

    given: "a screen object can be created using a factory method"
    def customerBrowseScreenObject = CustomerBrowseScreenObject.of(
            uiTestAPI, environment
    )

    and:
    customerBrowseScreenObject
            .searchForCustomer("Bob Ross")

    and: "a screen object can also be created via its constructor"
    def customerEditScreenObject = new CustomerEditScreenObject(
            uiTestAPI.getOpenedEditorScreen(CustomerEdit),
            environment,
            uiTestAPI
    )

    when:
    customerEditScreenObject
            .changeNameTo("Meggy Simpson")


    then:
    customerEditScreenObject
        .isClosed()

    and:
    customerBrowseScreenObject
        .isActive()
}
```

This variant allows to have a higher abstraction in the test case. It also decouples the test cases from the API of the Screen itself.

### Component Test API

The next concept of Sneferu is the Component Test API. This API is basically the same thing for a `Component` what the `ScreenTestAPI` is for a CUBA Screen. It is an abstraction on top of the CUBA `Component` APIs that is designed in the context of testing.

In order to use an instance of a Component Test API, it has to be created in the test case (or the Screen Object) via its factory method:

````groovy
import static com.haulmont.sneferu.ComponentDescriptors.*

// ...

customerBrowse
             .component(suggestionField("customerSearchField"))
             .search("Bob Ross")
````

`suggestionField` is a factory method that is statically imported from the `ComponentDescriptors` class. The parameter `customerSearchField` is the ID of the field used in the Screen XML descriptor.

It returns an instance of a subclass of `ComponentDescriptor`. In this case it returns an instance of `SuggestionFieldComponentDescriptor` which is aware of the specifics of this component (like searching the value via its `search` method).


#### Support custom components

Sneferu currently does not support all Components of CUBA. Furthermore if you use application specific components or composite components, Sneferu also can not support them out of the box.

Therefore it is possible to create custom Component Descriptors, that represent the Component in the testing scenario.

In order to support a new component, first a subclass of `ComponentTestAPI` needs to be created:

```java
public class SliderTestAPI extends GenericComponentTestAPI<Slider> {
    public SliderTestAPI(Slider component) {
        super(component);
    }

    public SliderTestAPI slideTo(int value) {
        rawComponent().setValue(value);
        return this;
    }
}
```

With that component specific Test API in place, a `ComponentDescriptor` can be created that is responsible for this `SliderTestAPI`:

```java
public class SliderComponentDescriptor
        extends GenericComponentDescriptor<Slider, SliderTestAPI> {

    public SliderComponentDescriptor(String componentId) {
        super(Slider.class, componentId);
    }

    @Override
    public SliderTestAPI createTestAPI(Slider component) {
        return new SliderTestAPI(component);
    }
}
```

The last step is to create a factory method that creates a new `SliderComponentDescriptor`:

```java
public class ApplicationComponentDescriptors {

    /**
     * creates a SliderComponentDescriptor instance
     * @param id the id of the component as defined in the screen XMl descriptor
     * @return a SliderComponentDescriptor instance
     */
    public static SliderComponentDescriptor slider(String id) {
        return new SliderComponentDescriptor(id);
    }
}
```

With those three steps, the custom component like the `Slider` in this example can be supported in the integration tests of the application.

### Interactions

The last remaining concept of Sneferu is the Interactions APIs. Interactions are what brings the screens to life. An interaction reflects any action a user would normally do manually to trigger some behavior or validate some result.

An interaction usage looks like this:

```groovy
import static com.haulmont.sneferu.ComponentDescriptors.button
import static com.haulmont.sneferu.Interactions.click

def "Interaction Usage"() {

    given:
    def customerBrowse = uiTestAPI
                            .openStandardLookup(Customer, CustomerBrowse)

    when: 'using the click interaction'
        customerBrowse
                .interact(
                        /* the click interaction */
                        click( 
                            /* the target of the click interaction */
                            button("createBtn")
                        )
                )
}
```

An interaction invoked on a `ScreenTestAPI` instance (like the `customerBrowse` instance in this case). Then it normally gets a target to act upon via a parameter (like the Component Descriptor `button("createBtn")` instance).

The Interaction then goes ahead and performs the desired action on the target component.

All interactions can be created via its Factory: `com.haulmont.sneferu.Interactions`. Normally from a readability point of view it once again makes sense to use static imports for them:

`import static com.haulmont.sneferu.Interactions.*`

There are two types of Interactions:

#### Chainable Interactions 
Chainable interactions are interactions that can be combined together via the `ScreenTestAPI` and with that represent a list of steps that should be executed against a Screen.

Those interactions do not have an outcome that can be retrieved programmatically in the test case. Examples of that are `click`, `select`, `openTab` etc.

The Screen Test API is a fluent API that allows you to chains interactions:

```groovy
def "Interactions can be chained"() {
    when:
    customerWithTabsEdit
             .interact(openTab(tabSheet("contentTabSheet"), "ordersTab"))
             .andThen(select(lookupField("orderType"), OrderType.BIG))
}
```
There are several alias methods, that can be used to underline the readability:

* `interact(Interaction interaction)`
* `andThen(Interaction interaction)`

#### Terminating Interactions

Terminating Interactions on the other hand return a value. By returning that value the chain of interactions is closed. An example of that is `closeEditor`. This interaction does two things:

1. closing the editor
2. returning the `OperationResult` object to the test case that is received from the StandardEditor instance.

In the test case the result can be retrieved and used for verification purposes:

```groovy
when:
OperationResult result = customerEdit
        .interact(enter(textField("nameField"), "Bob Ross"))
        .andThenGet(closeEditor())

then:
result == OperationResult.success()
```

Terminating Interactions can be invoked via one of the following alias methods in the `ScreenTestAPI`:

* `verify(InteractionWithOutcome interaction)`
* `get(InteractionWithOutcome interaction)`
* `interactAndGet(InteractionWithOutcome interaction)`
* `andThenGet(InteractionWithOutcome interaction)`
* `andThenVerify(InteractionWithOutcome interaction)`

#### Using different Interactions

Here is an example on how to use the two different types of interactions in a test case:

```groovy
import static com.haulmont.sneferu.ComponentDescriptors.*
import static com.haulmont.sneferu.Interactions.*

def "Chainable Interactions can be combined to perform a series of steps"() {

    when: 'an order is placed from a customer editor screen'
    OperationResult operationResult = 
    
         customerWithTabsEdit

             /* start of an interaction chain */
             .interact(openTab(tabSheet("contentTabSheet"), "ordersTab"))
             /* next interaction: select */
             .andThen(select(lookupField("orderType"), OrderType.BIG))
             /* next interaction: click */
             .andThen(click(button("placeOrderBtn")))
        
             /* terminating interaction: closeEditor */
             .andThenGet(closeEditor())
}
```

#### Custom Interactions

It is possible to define custom interaction that are not included in the Sneferu library (yet).

Taking the Slider example from above: the first step is to create a class defining the Interaction:

```java
import com.haulmont.sneferu.Interaction;
import com.haulmont.sneferu.screen.ScreenTestAPI;

public class SlideInteraction implements Interaction<ScreenTestAPI> {

    private final int value;
    private final SliderComponentDescriptor componentDescriptor;

    public SlideInteraction(SliderComponentDescriptor sliderComponentDescriptor, int value) {
        this.componentDescriptor = sliderComponentDescriptor;
        this.value = value;
    }

    @Override
    public void execute(ScreenTestAPI screenTestAPI) {
        ((Slider)screenTestAPI.component(componentDescriptor)
                .rawComponent())
                .setValue(value);
    }
}
```

After that, the second optional step is to define a factory method that instantiates a new `SlideInteraction` so that it can easily be used in the test case.

```java
public class ApplicationInteractions {

    public static <C extends Component, F extends ComponentTestAPI<C>> SlideInteraction slide(
            ComponentDescriptor<C, F> componentDescriptor,
            Object value
    ) {
        return new SlideInteraction(componentDescriptor, value);
    }
}
```


With that it is possible to use it directly in a test case:

```groovy
import static ApplicationInteractions.slide
import static ApplicationComponentDescriptors.slider

def "a slider component can be used through its custom interaction"() {

    when: 'an order is placed from a customer editor screen'
         customerEditor
             .interact(slide(slider("ageSlider"), 24))
}
```