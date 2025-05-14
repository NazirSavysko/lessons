package smartphone_factory.entity.smartphone_builder;

import smartphone_factory.entity.smartphone_entity.Smartphone;
import smartphone_factory.entity.smartphone_entity.TopUsaSmartphone;

public class TopUsaSmartphoneBuilder extends AbstractSmartphoneBuilder {
    @Override
    public Smartphone build() {
        return new TopUsaSmartphone(name, model);
    }
}
