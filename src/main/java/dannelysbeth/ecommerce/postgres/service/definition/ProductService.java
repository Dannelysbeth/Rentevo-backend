package dannelysbeth.ecommerce.postgres.service.definition;

import dannelysbeth.ecommerce.postgres.model.ProductItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface ProductService {

    void importFromFile(MultipartFile file);

    void saveMany(Set<ProductItem> productItems);
}
