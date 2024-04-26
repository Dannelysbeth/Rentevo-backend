package dannelysbeth.ecommerce.postgres.factory.definition;

import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JsonProductMapper {
    List<ProductRequest> readFromFile(MultipartFile file);
}
