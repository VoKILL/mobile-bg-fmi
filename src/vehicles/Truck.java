package vehicles;

public class Truck implements Product {
    private Long id;
    private String name;
    private String brand;
    private int year;
    private double range;

    public Truck(Long id, String name, String brand, int year, double range) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.range = range;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public double getRange() {
        return range;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRange(double range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Truck {id=" + id + ", name='" + name + "', brand='" + brand + "', year=" + year + ", range=" + range + "}";
    }
}
