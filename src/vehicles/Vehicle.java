package vehicles;

public abstract class Vehicle implements Product {
    private Long id;
    private String name;
    private String brand;
    private int year;
    private double range;

    public Vehicle(Long id, String name, String brand, int year, double range) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.range = range;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {id=" + id + ", name='" + name + "', brand='" + brand + "', year=" + year + ", range=" + range + "}";
    }
}
