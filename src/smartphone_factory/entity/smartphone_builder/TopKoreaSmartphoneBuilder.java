package smartphone_factory.entity.smartphone_builder;

import smartphone_factory.entity.smartphone_entity.Smartphone;
import smartphone_factory.entity.smartphone_entity.TopKoreaSmartphone;

public final class TopKoreaSmartphoneBuilder extends AbstractSmartphoneBuilder {

    @Override
    public Smartphone build() {
        return new TopKoreaSmartphone(name, model);
    }
}
