package dannelysbeth.ecommerce.postgres.api;

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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE')")
    @PostMapping("/import")
    public ResponseEntity<String> importProducts(@RequestPart("file") MultipartFile file) {
        this.productService.importFromFile(file);
        return ResponseEntity.ok()
                .body("Products were imported successfully");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE')")
    @PostMapping("/add")
    public ResponseEntity<String> importProducts(@RequestBody List<ProductRequest> productRequests) {
        Set<ProductItem> productItems = this.productMapper.transformFromRequest(productRequests);
        this.productService.saveMany(productItems);
        return ResponseEntity.ok()
                .body("Products were imported successfully");
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
                        .build());


    }
}
