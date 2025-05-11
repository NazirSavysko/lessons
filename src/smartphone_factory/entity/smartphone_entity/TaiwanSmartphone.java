package smartphone_factory.entity.smartphone_entity;

import smartphone_factory.entity.anotation.MidTierSmartphone;

@MidTierSmartphone
public final class TaiwanSmartphone extends Smartphone{
    private static final int MEMORY_CAPACITY = 128;
    private static final double SCREEN_SIZE = 6.0;

    public TaiwanSmartphone(final String name,final String model) {
        super(name, model, MEMORY_CAPACITY, SCREEN_SIZE);
    }

}
