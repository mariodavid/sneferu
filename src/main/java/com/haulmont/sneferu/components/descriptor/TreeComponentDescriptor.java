package com.haulmont.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.Tree;
import com.haulmont.sneferu.components.testapi.GenericComponentTestAPI;

public class TreeComponentDescriptor
        extends GenericComponentDescriptor<Tree, GenericComponentTestAPI<Tree>> {

    public TreeComponentDescriptor(String componentId) {
        super(Tree.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<Tree> createTestAPI(Tree component) {
        return new GenericComponentTestAPI<>(component);
    }
}
