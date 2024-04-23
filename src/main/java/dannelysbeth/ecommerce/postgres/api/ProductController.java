package dannelysbeth.ecommerce.postgres.api;

import dannelysbeth.ecommerce.postgres.mapper.definition.ProductMapper;
import dannelysbeth.ecommerce.postgres.model.DTO.request.ProductRequest;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import dannelysbeth.ecommerce.postgres.service.definition.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
}
