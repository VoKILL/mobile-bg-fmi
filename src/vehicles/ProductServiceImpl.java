package vehicles;

import java.util.ArrayList;
import java.util.List;
import search.Filter;

public class ProductServiceImpl implements ProductService {
    private List<Product> products = new ArrayList<>();

    @Override
    public Product createProduct(Product product) {
        setProductId(product, generateId());
        products.add(product);
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        for (Product p : products) {
            Long pId = getProductId(p);
            if (pId.equals(id)) {
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
            Long pId = getProductId(products.get(i));
            if (pId.equals(id)) {
                setProductId(updatedProduct, id);
                products.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> getProductId(p).equals(id));
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
            Long pId = getProductId(p);
            if (pId > maxId) {
                maxId = pId;
            }
        }
        return maxId + 1;
    }

    private Long getProductId(Product product) {
        if (product instanceof Car) {
            return ((Car)product).getId();
        } else if (product instanceof Truck) {
            return ((Truck)product).getId();
        }
        return null;
    }

    private void setProductId(Product product, Long id) {
        if (product instanceof Car) {
            ((Car)product).setId(id);
        } else if (product instanceof Truck) {
            ((Truck)product).setId(id);
        }
    }
}
