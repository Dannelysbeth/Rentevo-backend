package dannelysbeth.ecommerce.postgres.service.definition;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    void importFromFile(MultipartFile file);
}
