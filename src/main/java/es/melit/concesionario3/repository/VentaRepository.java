package es.melit.concesionario3.repository;

import es.melit.concesionario3.domain.Venta;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Venta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    // @Query("select venta from Venta venta where venta.compradorId.login = ?#{principal.username}")
    // List<Venta> findByCompradorIdIsCurrentUser();

    @Query("select venta from Venta venta where venta.vendedorId.login = ?#{principal.username}")
    List<Venta> findByVendedorIdIsCurrentUser();
}
