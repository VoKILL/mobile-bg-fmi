package Vehicles;

public class Car {
    protected String brand;
    protected String model;
    protected Integer year;
    protected String engine;
    protected String gearbox;

    public Car(String brand, String model, Integer year, String engine, String gearbox)
    {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.gearbox = gearbox;
    }

    public String getBrand()
    {
        return brand;
    }

    public String getModel()
    {
        return model;
    }

    public Integer getYear()
    {
        return year;
    }

    public String getEngine()
    {
        return engine;
    }

    public String getGearbox()
    {
        return gearbox;
    }
}