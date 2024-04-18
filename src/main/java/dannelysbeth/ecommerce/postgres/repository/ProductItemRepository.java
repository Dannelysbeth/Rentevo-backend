package dannelysbeth.ecommerce.postgres.repository;

import dannelysbeth.ecommerce.postgres.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductItemRepository extends JpaRepository<Product, Long> {
    Set<Product> getProductsByCategory(String category);

    Set<Product> getProductsByName(String productName);
}
