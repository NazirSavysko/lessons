package smartphone_factory.entity.smartphone_builder;

import smartphone_factory.entity.smartphone_entity.NoNameChinaSmartphone;
import smartphone_factory.entity.smartphone_entity.Smartphone;

public class NoNameChinaSmartphoneBuilder extends AbstractSmartphoneBuilder {
    @Override
    public Smartphone build() {
        return new NoNameChinaSmartphone(name, model);
    }
}
