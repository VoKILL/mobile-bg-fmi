package vehicles;

import java.util.List;
import search.Filter;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product updatedProduct);
    boolean deleteProduct(Long id);
    List<Product> findProducts(Filter<Product> filter);
}
