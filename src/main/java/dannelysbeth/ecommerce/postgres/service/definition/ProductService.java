package dannelysbeth.ecommerce.postgres.service.definition;

import dannelysbeth.ecommerce.postgres.model.Order;
import dannelysbeth.ecommerce.postgres.model.Product;
import dannelysbeth.ecommerce.postgres.model.ProductItem;

import java.util.List;
import java.util.Set;

public interface ProductService {

    double getRepositoryResponseTime();

    void saveMany(Set<ProductItem> productItems);

    Set<Product> getProducts(Double priceStartsAt, Double priceEndsAt, Long quantity, List<String> category, List<String> color, List<String> size);

    ProductItem getProductItemById(Long id);

    void decreaseProductItems(Order order);

    void deleteAll();
}
