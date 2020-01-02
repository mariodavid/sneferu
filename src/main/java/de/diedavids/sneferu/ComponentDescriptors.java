package de.diedavids.sneferu;


import de.diedavids.sneferu.components.descriptor.*;

/**
 * Factory methods for all Component Descriptors
 *
 * Normally should be included via a static import like:
 *
 * > import static de.diedavids.sneferu.ComponentDescriptors.*
 */
public class ComponentDescriptors {

    /**
     * creates a {@link GroupTableComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XMl descriptor
     * @return a GroupTableComponentDescriptor instance
     */
    public static GroupTableComponentDescriptor groupTable(String id) {
        return new GroupTableComponentDescriptor(id);
    }


    /**
     * creates a {@link TextFieldComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XMl descriptor
     * @return a TextFieldComponentDescriptor instance
     */
    public static TextFieldComponentDescriptor textField(String id) {
        return new TextFieldComponentDescriptor(id);
    }


    /**
     * creates a {@link DateFieldComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XMl descriptor
     * @return a DateFieldComponentDescriptor instance
     */
    public static DateFieldComponentDescriptor dateField(String id) {
        return new DateFieldComponentDescriptor(id);
    }


    /**
     * creates a {@link SuggestionFieldComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XMl descriptor
     * @return a SuggestionFieldComponentDescriptor instance
     */
    public static SuggestionFieldComponentDescriptor suggestionField(String id) {
        return new SuggestionFieldComponentDescriptor(id);
    }


    /**
     * creates a {@link ButtonComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XMl descriptor
     * @return a ButtonComponentDescriptor instance
     */
    public static ButtonComponentDescriptor button(String id) {
        return new ButtonComponentDescriptor(id);
    }


    /**
     * creates a {@link TabsheetComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XMl descriptor
     * @return a TabsheetComponentDescriptor instance
     */
    public static TabsheetComponentDescriptor tabSheet(String id) {
        return new TabsheetComponentDescriptor(id);
    }

}
