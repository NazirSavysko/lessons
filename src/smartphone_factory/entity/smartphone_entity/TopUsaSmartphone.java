package smartphone_factory.entity.smartphone_entity;

import smartphone_factory.entity.anotation.FlagshipSmartphone;

@FlagshipSmartphone
public final class TopUsaSmartphone extends Smartphone {
    private static final int MEMORY_CAPACITY = 1024;
    private static final double SCREEN_SIZE = 6.5;

    public TopUsaSmartphone(final String name,final String model) {
        super(name, model, MEMORY_CAPACITY, SCREEN_SIZE);
    }

}
