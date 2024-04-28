package dannelysbeth.ecommerce.postgres.mapper.definition;

import dannelysbeth.ecommerce.postgres.model.Cart;
import dannelysbeth.ecommerce.postgres.model.CartItem;
import dannelysbeth.ecommerce.postgres.model.DTO.response.CartResponse;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CartMapper {
    CartItem getCartItemFromProductItem(ProductItem productItem, Cart cart);

    CartResponse transformToCartResponse(Cart cart);

}
