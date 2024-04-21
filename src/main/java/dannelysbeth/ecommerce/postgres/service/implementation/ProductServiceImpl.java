package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.mapper.definition.ProductMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import dannelysbeth.ecommerce.postgres.repository.ProductItemRepository;
import dannelysbeth.ecommerce.postgres.service.definition.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductItemRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public void importFromFile(MultipartFile file) {
        List<ProductRequest> productsReq = this.productMapper.readFromFile(file);
        List<ProductItem> products  = this.productMapper.transformFromRequest(productsReq);
        this.productRepository.saveAll(products);
    }

}
