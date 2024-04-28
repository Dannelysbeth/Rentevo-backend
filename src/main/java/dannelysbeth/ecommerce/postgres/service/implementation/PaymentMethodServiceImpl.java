package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.exception.PaymentMethodNotFoundException;
import dannelysbeth.ecommerce.postgres.model.UserPaymentMethod;
import dannelysbeth.ecommerce.postgres.repository.PaymentMethodRepository;
import dannelysbeth.ecommerce.postgres.service.definition.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {
    final private PaymentMethodRepository paymentMethodRepository;
    @Override
    public UserPaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id).orElseThrow(PaymentMethodNotFoundException::new);
    }
}
