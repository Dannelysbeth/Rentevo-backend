package dannelysbeth.ecommerce.postgres.mapper.definition;

import dannelysbeth.ecommerce.postgres.model.*;
import dannelysbeth.ecommerce.postgres.model.DTO.request.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderMapper {

    Order initOrder(User user);
    Order updateOrderFromRequest(Order order, OrderRequest request, Cart cart, Address address, ShippingMethod shippingMethod);
}
