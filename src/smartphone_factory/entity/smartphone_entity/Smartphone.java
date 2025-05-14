package smartphone_factory.entity.smartphone_entity;


public abstract class Smartphone implements Cloneable {
    private final String name;
    private final String model;
    private final int memoryCapacity;
    private final double screenSize;


    protected Smartphone(final String name, final String model, final int memoryCapacity, final double screenSize) {
        this.name = name;
        this.model = model;
        this.memoryCapacity = memoryCapacity;
        this.screenSize = screenSize;
    }

    @Override
    public Smartphone clone() {
        try {
            return (Smartphone) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
