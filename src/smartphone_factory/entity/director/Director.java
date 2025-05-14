package smartphone_factory.entity.director;

import smartphone_factory.entity.smartphone_builder.AbstractSmartphoneBuilder;
import smartphone_factory.entity.smartphone_entity.Smartphone;

public final class Director {
    private final String name;
    private final String model;

    public Director(final String name, final String model) {
        this.name = name;
        this.model = model;
    }

    public Smartphone buildSmartphone(final AbstractSmartphoneBuilder smartphoneBuilder) {
        smartphoneBuilder.setName(this.name);
        smartphoneBuilder.setModel(this.model);

       return smartphoneBuilder.build();
    }
}
