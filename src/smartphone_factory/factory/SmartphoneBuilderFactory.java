package smartphone_factory.factory;

import smartphone_factory.entity.smartphone_builder.*;
import smartphone_factory.entity.smartphone_entity.Smartphone;

public final class SmartphoneBuilderFactory {
    private SmartphoneBuilderFactory() {

    }

    public static AbstractSmartphoneBuilder getSmartphoneBuilder(final Class<? extends Smartphone> smartphoneClassByCategory)
    {
        switch (smartphoneClassByCategory.getSimpleName()) {
            case "TopKoreaSmartphone" -> {
                return new TopKoreaSmartphoneBuilder();
            }
            case "TopUsaSmartphone" -> {
                return new TopUsaSmartphoneBuilder();
            }
            case "NoNameIndiaSmartphone" -> {
                return new NoNameIndiaSmartphoneBuilder();
            }
            case "NoNameChinaSmartphone" -> {
                return new NoNameChinaSmartphoneBuilder();
            }
            case "TaiwanSmartphone" -> {
                return new TaiwanSmartphoneBuilder();
            }
            default -> throw new IllegalArgumentException("Unknown smartphone class: " + smartphoneClassByCategory);
        }
    }
}
