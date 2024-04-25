package dannelysbeth.ecommerce.postgres.repository;

import dannelysbeth.ecommerce.postgres.model.VariationOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VariationOptionRepository extends JpaRepository<VariationOption, Long> {
    boolean existsByVariation_ParameterAndValue(String parameter, String name);

    VariationOption getByVariation_ParameterAndValue(String parameter, String name);
}
