package smartphone_factory.entity.smartphone_entity;

import smartphone_factory.entity.anotation.FlagshipSmartphone;

@FlagshipSmartphone
public final class TopKoreaSmartphone extends Smartphone {
    private static final int MEMORY_CAPACITY = 512;
    private static final double SCREEN_SIZE = 6.8;

    public TopKoreaSmartphone(final String name, final String model) {
        super(name,model, MEMORY_CAPACITY, SCREEN_SIZE);
    }
}
