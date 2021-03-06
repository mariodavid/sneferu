package de.diedavids.sneferu.components.descriptor;


import com.haulmont.cuba.gui.components.Image;
import de.diedavids.sneferu.components.testapi.GenericComponentTestAPI;

public class ImageComponentDescriptor
        extends GenericComponentDescriptor<Image, GenericComponentTestAPI<Image>> {

    public ImageComponentDescriptor(String componentId) {
        super(Image.class, componentId);
    }

    @Override
    public GenericComponentTestAPI<Image> createTestAPI(Image component) {
        return new GenericComponentTestAPI<>(component);
    }
}
