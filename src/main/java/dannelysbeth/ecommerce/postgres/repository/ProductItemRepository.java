package dannelysbeth.ecommerce.postgres.repository;

import dannelysbeth.ecommerce.postgres.model.Product;
import dannelysbeth.ecommerce.postgres.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
//    Set<Product> getProductsByCategory(String category);

//    Set<Product> getProductsByName(String productName);
}