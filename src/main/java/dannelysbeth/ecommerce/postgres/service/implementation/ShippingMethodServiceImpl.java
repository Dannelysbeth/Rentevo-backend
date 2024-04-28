package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.exception.ShippingMethodNotFoundException;
import dannelysbeth.ecommerce.postgres.model.ShippingMethod;
import dannelysbeth.ecommerce.postgres.repository.ShippingMethodRepository;
import dannelysbeth.ecommerce.postgres.service.definition.ShippingMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingMethodServiceImpl implements ShippingMethodService {

    private final ShippingMethodRepository shippingMethodRepository;

    @Override
    public ShippingMethod getShippingMethodByCode(String code) {
        return shippingMethodRepository.findById(code).orElseThrow(ShippingMethodNotFoundException::new);
    }
}
