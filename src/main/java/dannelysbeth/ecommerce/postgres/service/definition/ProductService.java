package dannelysbeth.ecommerce.postgres.service.definition;

import dannelysbeth.ecommerce.postgres.model.Address;
import dannelysbeth.ecommerce.postgres.model.Product;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import dannelysbeth.ecommerce.postgres.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface ProductService {

    void importFromFile(MultipartFile file);

    void saveMany(Set<ProductItem> productItems);

    Set<Product> getProducts(Double priceStartsAt, Double priceEndsAt);
}
