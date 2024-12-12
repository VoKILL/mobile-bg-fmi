package vehicles;

import java.util.ArrayList;
import java.util.List;
import search.Filter;

public class ProductServiceImpl implements ProductService {
    private List<Product> products = new ArrayList<>();

    @Override
    public Product createProduct(Product product) {
        if (product.getId() == null && product instanceof Vehicle) {
            ((Vehicle) product).setId(generateId());
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
            Product current = products.get(i);
            if (current.getId().equals(id)) {
                if (updatedProduct instanceof Vehicle) {
                    ((Vehicle) updatedProduct).setId(id);
                }
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

    @Override
    public List<Product> findProducts(Filter<Product> filter) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (filter.matches(p)) {
                result.add(p);
            }
        }
        return result;
    }

    private Long generateId() {
        long maxId = 0;
        for (Product p : products) {
            Long pId = p.getId();
            if (pId != null && pId > maxId) {
                maxId = pId;
            }
        }
        return maxId + 1;
    }
}
