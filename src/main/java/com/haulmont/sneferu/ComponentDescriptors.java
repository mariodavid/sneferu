package com.haulmont.sneferu;


import com.haulmont.sneferu.components.descriptor.*;

/**
 * Factory methods for all Component Descriptors
 *
 * Normally should be included via a static import like:
 *
 * > import static com.haulmont.sneferu.ComponentDescriptors.*
 */
public class ComponentDescriptors {

    /**
     * creates a {@link GroupTableComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a GroupTableComponentDescriptor instance
     */
    public static GroupTableComponentDescriptor groupTable(String id) {
        return new GroupTableComponentDescriptor(id);
    }

    /**
     * creates a {@link TableComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a TableComponentDescriptor instance
     */
    public static TableComponentDescriptor table(String id) {
        return new TableComponentDescriptor(id);
    }

    /**
     * creates a {@link DataGridComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a DataGridComponentDescriptor instance
     */
    public static DataGridComponentDescriptor dataGrid(String id) {
        return new DataGridComponentDescriptor(id);
    }


    /**
     * creates a {@link TreeDataGridComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a TreeDataGridComponentDescriptor instance
     */
    public static TreeDataGridComponentDescriptor treeDataGrid(String id) {
        return new TreeDataGridComponentDescriptor(id);
    }

    /**
     * creates a {@link CalendarComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a CalendarComponentDescriptor instance
     */
    public static CalendarComponentDescriptor calendar(String id) {
        return new CalendarComponentDescriptor(id);
    }


    /**
     * creates a {@link TextFieldComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a TextFieldComponentDescriptor instance
     */
    public static TextFieldComponentDescriptor textField(String id) {
        return new TextFieldComponentDescriptor(id);
    }

    /**
     * creates a {@link TextInputFieldComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a TextInputFieldComponentDescriptor instance
     */
    public static TextInputFieldComponentDescriptor textInputField(String id) {
        return new TextInputFieldComponentDescriptor(id);
    }


    /**
     * creates a {@link LookupFieldComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a LookupFieldComponentDescriptor instance
     */
    public static LookupFieldComponentDescriptor lookupField(String id) {
        return new LookupFieldComponentDescriptor(id);
    }

    /**
     * creates a {@link PickerFieldComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a PickerFieldComponentDescriptor instance
     */
    public static PickerFieldComponentDescriptor pickerField(String id) {
        return new PickerFieldComponentDescriptor(id);
    }


    /**
     * creates a {@link DateFieldComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a DateFieldComponentDescriptor instance
     */
    public static DateFieldComponentDescriptor dateField(String id) {
        return new DateFieldComponentDescriptor(id);
    }


    /**
     * creates a {@link CheckBoxComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a CheckBoxComponentDescriptor instance
     */
    public static CheckBoxComponentDescriptor checkBox(String id) {
        return new CheckBoxComponentDescriptor(id);
    }


    /**
     * creates a {@link SuggestionFieldComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a SuggestionFieldComponentDescriptor instance
     */
    public static SuggestionFieldComponentDescriptor suggestionField(String id) {
        return new SuggestionFieldComponentDescriptor(id);
    }


    /**
     * creates a {@link ButtonComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a ButtonComponentDescriptor instance
     */
    public static ButtonComponentDescriptor button(String id) {
        return new ButtonComponentDescriptor(id);
    }


    /**
     * creates a {@link ImageComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a ImageComponentDescriptor instance
     */
    public static ImageComponentDescriptor image(String id) {
        return new ImageComponentDescriptor(id);
    }


    /**
     * creates a {@link TabsheetComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a TabsheetComponentDescriptor instance
     */
    public static TabsheetComponentDescriptor tabSheet(String id) {
        return new TabsheetComponentDescriptor(id);
    }


    /**
     * creates a {@link AccordionComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a AccordionComponentDescriptor instance
     */
    public static AccordionComponentDescriptor accordion(String id) {
        return new AccordionComponentDescriptor(id);
    }


    /**
     * creates a {@link TreeComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a TreeComponentDescriptor instance
     */
    public static TreeComponentDescriptor tree(String id) {
        return new TreeComponentDescriptor(id);
    }


    /**
     * creates a {@link ColorPickerComponentDescriptor} instance
     * @param id the id of the component as defined in the screen XML descriptor
     * @return a ColorPickerComponentDescriptor instance
     */
    public static ColorPickerComponentDescriptor colorPicker(String id) {
        return new ColorPickerComponentDescriptor(id);
    }

}
