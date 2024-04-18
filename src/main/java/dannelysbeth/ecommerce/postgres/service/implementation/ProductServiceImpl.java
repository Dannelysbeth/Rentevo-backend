package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.mapper.definition.ProductMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import dannelysbeth.ecommerce.postgres.repository.ProductItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {

    private final ProductItemRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public void importFromFile(MultipartFile file) {
        List<ProductRequest> products = this.productMapper.readFromFile(file);
        this.productRepository.saveAll(products);
    }

}
