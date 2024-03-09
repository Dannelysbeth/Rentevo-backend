package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.repository;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Set<Product> getProductsByProductCategory_Id(Long categoryId);
    Set<Product> getProductsByName(String productName);
}
