package smartphone_factory.entity;

public final class Smartphone {
    private final String name;
    private final String model;
    private final int memoryCapacity;
    private final double screenSize;

    public Smartphone(String name, String model, int memoryCapacity, double screenSize) {
        this.name = name;
        this.model = model;
        this.memoryCapacity = memoryCapacity;
        this.screenSize = screenSize;
    }

    public String getName() {
        return name;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public String getModel() {
        return model;
    }
}
