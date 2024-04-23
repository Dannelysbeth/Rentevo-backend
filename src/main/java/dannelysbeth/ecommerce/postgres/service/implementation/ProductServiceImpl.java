package dannelysbeth.ecommerce.postgres.service.implementation;

import dannelysbeth.ecommerce.postgres.mapper.definition.ProductMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import dannelysbeth.ecommerce.postgres.model.Variation;
import dannelysbeth.ecommerce.postgres.model.VariationOption;
import dannelysbeth.ecommerce.postgres.repository.ProductItemRepository;
import dannelysbeth.ecommerce.postgres.repository.ProductRepository;
import dannelysbeth.ecommerce.postgres.repository.VariationOptionRepository;
import dannelysbeth.ecommerce.postgres.repository.VariationRepository;
import dannelysbeth.ecommerce.postgres.service.definition.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductItemRepository productItemRepository;
    private final VariationRepository variationRepository;
    private final VariationOptionRepository variationOptionRepository;

    private final ProductMapper productMapper;
    @Override
    public void importFromFile(MultipartFile file) {
        List<ProductRequest> productsReq = this.productMapper.readFromFile(file);
        Set<ProductItem> products  = this.productMapper.transformFromRequest(productsReq);
        this.productItemRepository.saveAll(products);
    }

    @Override
    public void saveMany(Set<ProductItem> productItems) {
        productItems.forEach(item->{
            if (this.productRepository.existsById(item.getProduct().getId())) {
                this.productRepository.save(item.getProduct());
                saveVariationOptions(item.getVariationOptions());
            }
            this.productItemRepository.save(item);
        });
    }
    private void saveVariationOptions(Set<VariationOption> variationOptions) {
        variationOptions.forEach(variationOption -> {
            if (!this.variationOptionRepository.existsByVariation_ParameterAndValue(variationOption.getVariation().getParameter(), variationOption.getValue())) {
                if (!this.variationRepository.existsByParameter(variationOption.getVariation().getParameter())) {
                    this.variationRepository.save(variationOption.getVariation());
                }
                this.variationOptionRepository.save(variationOption);
            }
        });
    }

}
