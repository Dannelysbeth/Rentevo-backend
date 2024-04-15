package dannelysbeth.ecommerce.postgres.mapper.definition;

import dannelysbeth.ecommerce.postgres.model.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {
    List<ProductItem> readFromFile(MultipartFile file);
}
