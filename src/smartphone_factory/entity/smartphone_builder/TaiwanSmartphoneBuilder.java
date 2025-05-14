package smartphone_factory.entity.smartphone_builder;

import smartphone_factory.entity.smartphone_entity.Smartphone;
import smartphone_factory.entity.smartphone_entity.TaiwanSmartphone;

public class TaiwanSmartphoneBuilder extends AbstractSmartphoneBuilder {
    @Override
    public Smartphone build() {
        return new TaiwanSmartphone(name, model);
    }
}
