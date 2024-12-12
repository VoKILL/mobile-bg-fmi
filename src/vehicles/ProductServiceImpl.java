package vehicles;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private List<Product> products = new ArrayList<>();

    @Override
    public Product createProduct(Product product) {
        if (product.getId() == null) {
            product.setId(generateId());
        }
        products.add(product);
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                updatedProduct.setId(id);
                products.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }

    private Long generateId() {
        long maxId = 0;
        for (Product p : products) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }
}
