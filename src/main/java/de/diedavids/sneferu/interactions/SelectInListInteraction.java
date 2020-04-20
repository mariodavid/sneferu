package de.diedavids.sneferu.interactions;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.ListComponent;
import de.diedavids.sneferu.Interaction;
import de.diedavids.sneferu.components.ComponentDescriptor;
import de.diedavids.sneferu.components.testapi.ComponentTestAPI;
import de.diedavids.sneferu.screen.ScreenTestAPI;
import java.util.Collection;
import java.util.Collections;

public class SelectInListInteraction<E extends Entity> implements Interaction {

    private final ComponentDescriptor<? extends Component, ? extends ComponentTestAPI> componentDescriptor;
    private final Collection<E> values;

    public <C extends Component, F extends ComponentTestAPI<C>> SelectInListInteraction(
        ComponentDescriptor<C, F> componentDescriptor,
        E value
    ) {
        this.componentDescriptor = componentDescriptor;
        this.values = Collections.singleton(value);
    }

    public <C extends Component, F extends ComponentTestAPI<C>> SelectInListInteraction(
        ComponentDescriptor<C, F> componentDescriptor,
        Collection<E> values
    ) {
        this.componentDescriptor = componentDescriptor;
        this.values = values;
    }


    @Override
    public void execute(ScreenTestAPI screenTestAPI) {
        ListComponent listComponent = (ListComponent) screenTestAPI.component(componentDescriptor).rawComponent();
        listComponent.setSelected(values);
    }


}

