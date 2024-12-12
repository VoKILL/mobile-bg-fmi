package ads;

import vehicles.Product;

public class Ad {
    private Long id;
    private Product product;
    private double price;
    private String description;

    public Ad(Long id, Product product, double price, String description) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ad {id=" + id +
                ", product=" + (product != null ? product.getBrand() + " " + product.getName() : "null") +
                ", price=" + price +
                ", description='" + description + "'}";
    }
}
