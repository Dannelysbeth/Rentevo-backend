package dannelysbeth.ecommerce.postgres.repository;

import dannelysbeth.ecommerce.postgres.model.Variation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariationRepository extends JpaRepository<Variation, Long> {
    boolean existsByParameter(String parameter);

    Variation getByParameter(String parameter);
}
