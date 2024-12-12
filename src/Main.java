import vehicles.Product;
import vehicles.ProductService;
import vehicles.ProductServiceImpl;
import search.*; // Импортираме всички филтри

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductServiceImpl();

        // Създаваме няколко продукта
        Product p1 = new Product(null, "iPhone 12", 999.99);
        Product p2 = new Product(null, "Samsung Galaxy S21", 899.99);
        Product p3 = new Product(null, "Xiaomi Mi 11", 499.99);

        productService.createProduct(p1);
        productService.createProduct(p2);
        productService.createProduct(p3);

        System.out.println("All products:");
        for (Product product : productService.getAllProducts()) {
            System.out.println(product);
        }

        // Търсим продукт по ID
        Long searchId = 2L;
        Product found = productService.getProductById(searchId);
        System.out.println("Found product with ID " + searchId + ": " + found);

        // Ъпдейт на продукт
        Product updated = new Product(null, "Samsung Galaxy S21 Ultra", 1099.99);
        productService.updateProduct(2L, updated);
        System.out.println("Updated product with ID 2: " + productService.getProductById(2L));

        // Изтриваме продукт
        productService.deleteProduct(1L);
        System.out.println("After deletion of ID 1:");
        for (Product product : productService.getAllProducts()) {
            System.out.println(product);
        }

        // Пример за филтриране
        // 1. CaseInsensitiveFilter по име
        Filter<Product> nameFilter = new CaseInsensitiveFilter<>(
                Product::getName,
                "Xiaomi Mi 11"
        );
        System.out.println("Products matching name 'Xiaomi Mi 11':");
        for (Product product : productService.findProducts(nameFilter)) {
            System.out.println(product);
        }

        // 2. RangeFilter по цена (между 500 и 1200)
        Filter<Product> priceRangeFilter = new RangeFilter<>(
                Product::getPrice,
                500.0,
                1200.0
        );
        System.out.println("Products with price between 500 and 1200:");
        for (Product product : productService.findProducts(priceRangeFilter)) {
            System.out.println(product);
        }

        // 3. ExactValueFilter по точно съвпадение на цена
        Filter<Product> exactPriceFilter = new ExactValueFilter<>(
                Product::getPrice,
                1099.99
        );
        System.out.println("Products with exact price 1099.99:");
        for (Product product : productService.findProducts(exactPriceFilter)) {
            System.out.println(product);
        }
    }
}
