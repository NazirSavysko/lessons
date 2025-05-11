package smartphone_factory.entity.smartphone_entity;

public abstract class Smartphone {
    private final String name;
    private final String model;
    private final int memoryCapacity;
    private final double screenSize;

    public Smartphone(final String name,final String model,final int memoryCapacity,final double screenSize) {
        this.name = name;
        this.model = model;
        this.memoryCapacity = memoryCapacity;
        this.screenSize = screenSize;
    }

    public String getName() {
        return this.name;
    }
    public String getModel() {
        return this.model;
    }
    public int getMemoryCapacity() {
        return this.memoryCapacity;
    }
    public double getScreenSize() {
        return this.screenSize;
    }
}
