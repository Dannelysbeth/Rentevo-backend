package dannelysbeth.ecommerce.postgres.mapper.definition;

import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import dannelysbeth.ecommerce.postgres.model.DTO.response.ProductItemResponse;
import dannelysbeth.ecommerce.postgres.model.DTO.response.ProductResponse;
import dannelysbeth.ecommerce.postgres.model.Product;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    Set<ProductItem> transformFromRequest(List<ProductRequest> requests);

    Set<ProductItemResponse> transformToProductItemResponse(Set<ProductItem> productItems);

    Set<ProductResponse> transformToProductResponse(Set<Product> products);
}
