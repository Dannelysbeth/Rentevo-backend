package dannelysbeth.ecommerce.postgres.api;

import dannelysbeth.ecommerce.postgres.factory.definition.JsonProductMapper;
import dannelysbeth.ecommerce.postgres.mapper.definition.ProductMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import dannelysbeth.ecommerce.postgres.model.DTO.response.GlobalResponse;
import dannelysbeth.ecommerce.postgres.model.DTO.response.ProductResponse;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import dannelysbeth.ecommerce.postgres.service.definition.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;
    private final JsonProductMapper jsonProductMapper;


    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE')")
    @PostMapping("/import")
    public ResponseEntity<GlobalResponse> importProducts(@RequestPart("file") MultipartFile file) {
        List<ProductRequest> productsReq = this.jsonProductMapper.readFromFile(file);
        Set<ProductItem> products = this.productMapper.transformFromRequest(productsReq);
        this.productService.saveMany(products);
        return ResponseEntity.ok()
                .body(GlobalResponse.builder()
                        .entries(new HashSet<>(Collections.singleton("Products were imported successfully")))
                        .responseTime(productService.getRepositoryResponseTime()+"ms")
                        .build());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE')")
    @PostMapping("/add")
    public ResponseEntity<GlobalResponse> importProducts(@RequestBody List<ProductRequest> productRequests) {
        Set<ProductItem> productItems = this.productMapper.transformFromRequest(productRequests);
        this.productService.saveMany(productItems);
        return ResponseEntity.ok()
                .body(GlobalResponse.builder()
                        .entries(new HashSet<>(Collections.singleton("Products were imported successfully")))
                        .responseTime(productService.getRepositoryResponseTime()+"ms")
                        .build());
    }

    @GetMapping
    public ResponseEntity<GlobalResponse> findAll(@RequestParam(required = false) Double lte,
                                                  @RequestParam(required = false) Double gte,
                                                  @RequestParam(defaultValue = "1") Long minQuantity,
                                                  @RequestParam(required = false) String[] category,
                                                  @RequestParam(required = false) String[] color,
                                                  @RequestParam(required = false) String... size) {

        Set<ProductResponse> responses = productMapper.transformToProductResponse(productService.getProducts(
                lte, gte, minQuantity,
                (category == null ? null : Arrays.asList(category)),
                (color == null ? null : Arrays.asList(color)),
                (size == null ? null : Arrays.asList(size))));
        return ResponseEntity.ok()
                .body(GlobalResponse.builder()
                        .entries(Collections.singleton(responses))
                        .count(responses.size())
                        .responseTime(this.productService.getRepositoryResponseTime()+"ms")
                        .build());


    }
}
