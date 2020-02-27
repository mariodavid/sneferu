package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.TokenList;
import de.diedavids.sneferu.components.testapi.GenericComponentTestAPI;

public class TokenListComponentDescriptor
        extends GenericComponentDescriptor<TokenList, GenericComponentTestAPI<TokenList>> {

    public TokenListComponentDescriptor(String componentId) {
        super(TokenList.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<TokenList> createTestAPI(TokenList component) {
        return new GenericComponentTestAPI<>(component);
    }
}
