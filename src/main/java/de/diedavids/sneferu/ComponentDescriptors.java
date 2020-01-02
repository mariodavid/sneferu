package de.diedavids.sneferu;


import de.diedavids.sneferu.components.descriptor.*;

public class ComponentDescriptors {

    public static GroupTableComponentDescriptor groupTable(String id) {
        return new GroupTableComponentDescriptor(id);
    }

    public static TextFieldComponentDescriptor textField(String id) {
        return new TextFieldComponentDescriptor(id);
    }

    public static DateFieldComponentDescriptor dateField(String id) {
        return new DateFieldComponentDescriptor(id);
    }

    public static SuggestionFieldComponentDescriptor suggestionField(String id) {
        return new SuggestionFieldComponentDescriptor(id);
    }

    public static ButtonComponentDescriptor button(String id) {
        return new ButtonComponentDescriptor(id);
    }

    public static TabsheetComponentDescriptor tabSheet(String id) {
        return new TabsheetComponentDescriptor(id);
    }

}
