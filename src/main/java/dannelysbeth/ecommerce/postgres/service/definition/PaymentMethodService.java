package dannelysbeth.ecommerce.postgres.service.definition;

import dannelysbeth.ecommerce.postgres.model.UserPaymentMethod;

public interface PaymentMethodService {
    UserPaymentMethod getPaymentMethodById(Long id);
}
