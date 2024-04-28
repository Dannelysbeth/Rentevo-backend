package dannelysbeth.ecommerce.postgres.service.definition;

import dannelysbeth.ecommerce.postgres.model.ShippingMethod;

public interface ShippingMethodService {
    ShippingMethod getShippingMethodByCode(String code);
}
