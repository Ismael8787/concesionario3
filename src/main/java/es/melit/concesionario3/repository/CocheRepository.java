package es.melit.concesionario3.repository;

import es.melit.concesionario3.domain.Coche;
import io.micrometer.core.lang.Nullable;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Coche entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CocheRepository extends JpaRepository<Coche, Long>, JpaSpecificationExecutor<Coche> {
    List<Coche> findByVendidoFalse();
    // List<Coche> findByVendido(@Nullable Specification<Coche> specification);
}
