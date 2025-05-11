package smartphone_factory.entity.smartphone_entity;

import smartphone_factory.entity.anotation.BudgetSmartphone;


@BudgetSmartphone
public class NoNameChinaSmartphone extends Smartphone{
    private static final int MEMORY_CAPACITY = 32;
    private static final double SCREEN_SIZE = 5.0;

    public NoNameChinaSmartphone(final String name,final String model) {
        super(name, model,MEMORY_CAPACITY, SCREEN_SIZE);
    }
}
