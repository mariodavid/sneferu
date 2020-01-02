## Sneferu

Sneferu is a testing library to simplify web integration testing for a CUBA application.
It consists of a couple of APIs that allow the application developer to express interactions with 
UI screens in a fluent API. 


### Overview

Testing of a CUBA application on the web-layer previously consists of two extremes:

1. write unit test for the business logic in the screen controllers
2. write a functional UI test that executes the application through the browser

Both of those extremes have their downsides. In CUBA 7.1 web-integration testing was introduced.

Sneferu is taking that new addition and brings it to the next level. With that it covers the broad
middle ground between those two extremes.

Sneferu works with different testing frameworks, like JUnit and Spock.

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

// TODO: how to add library to a CUBA application

#### JUnit

#### Spock



### UI Test API

The UI Test API is the interaction point in the test case that manages screens. 
It allows to open / close screens and retrieve information about opened screens.
 
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

### Screen Test API

The next API of Sneferu allows to interact with a particular Screen. 
It consists of two main use-cases:

1. apply interactions (see [Interactions](#Interactions)) on a particular screen
2. retrieve components of a screen to interact with directly

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

The Screen Test API is a fluent API that allows to express chains of interactions:


```groovy
def "Interactions can be chained"() {

    when: 'an order is placed from a customer editor screen'
    customerWithTabsEdit
             .interact(openTab(tabSheet("contentTabSheet"), "ordersTab"))
             .andThen(select(lookupField("orderType"), OrderType.BIG))
             .andThen(click(button("placeOrderBtn")))
             .andThenGet(closeEditor())
}
```

There are several alias names, that can be used to underline the readability:

* `interact(Interaction interaction)`
* `andThen(Interaction interaction)`

For Interactions that return a result, that should be used in the test case, the following method names
are available (alias names for the same functionality):

* `verify(InteractionWithOutcome interaction)`
* `get(InteractionWithOutcome interaction)`
* `interactAndGet(InteractionWithOutcome interaction)`
* `andThenGet(InteractionWithOutcome interaction)`
* `andThenVerify(InteractionWithOutcome interaction)`


### Component Test API

#### Support custom components

### Interactions

#### Interactions with Outcome

#### Custom Interactions


### Page Objects












#### Usage via Page Objects

Instead of using the API directly, it is also possible to create a [Page Object](https://martinfowler.com/bliki/PageObject.html)
that represents the API of a particular Page / Screen of the UI. This allows to create a dedicated abstraction between
the test case and the screen that is under test. 

##### Customer Browse Test API (Page Object)
```java

public class CustomerBrowsePageObject implements PageObject<StandardLookupTestAPI<CustomerBrowse>> {

    private final UiTestAPI uiTestAPI;
    private StandardLookupTestAPI<CustomerBrowse> delegate;
    private final TestUiEnvironment testUiEnvironment;

    // ...

    public CustomerBrowsePageObject searchForCustomer(Customer customer) {
        delegate
                .component(suggestionField("quickSearch"))
                .search(customer);

        return this;
    }

    public CustomerBrowsePageObject searchForCustomer(String customerName) {

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


##### Test Case with Page Object
```groovy

def "screens can be used through its Page Object Test API"() {

    given:
    def customerBrowsePageObject = CustomerBrowsePageObject.of(
            uiTestAPI, environment
    )

    and:
    customerBrowsePageObject
            .searchForCustomer("Bob Ross")

    and:
    def customerEditPageObject = new CustomerEditPageObject(
            uiTestAPI.getOpenedEditorScreen(CustomerEdit),
            environment,
            uiTestAPI
    )

    when:
    customerEditPageObject
            .changeNameTo("Meggy Simpson")


    then:
    customerEditPageObject
        .isClosed()

    and:
    customerBrowsePageObject
        .isActive()
}
```

This variant allows to have a higher abstraction in the test case. 
It also decouples the test cases from the API of the Screen itself.