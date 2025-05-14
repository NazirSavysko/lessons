package smartphone_factory.entity.smartphone_builder;

import smartphone_factory.entity.smartphone_entity.Smartphone;

public abstract class AbstractSmartphoneBuilder {
    protected String name;
    protected String model;

    public abstract Smartphone build();
    public  void setName(String name){
        this.name = name;
    }

    public  void setModel(String model){
        this.model = model;
    }
}
