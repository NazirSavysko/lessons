package smartphone_factory.entity.smartphone_builder;

import smartphone_factory.entity.smartphone_entity.NoNameIndiaSmartphone;
import smartphone_factory.entity.smartphone_entity.Smartphone;

public class NoNameIndiaSmartphoneBuilder extends AbstractSmartphoneBuilder {
    @Override
    public Smartphone build() {
        return new NoNameIndiaSmartphone(name, model);
    }
}
