package es.melit.concesionario3.service;

import es.melit.concesionario3.domain.Coche;
import es.melit.concesionario3.domain.Venta;
import es.melit.concesionario3.repository.CocheRepository;
import es.melit.concesionario3.repository.VentaRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Coche}.
 */
@Service
@Transactional
public class CocheService {

    private final Logger log = LoggerFactory.getLogger(CocheService.class);

    private final CocheRepository cocheRepository;
    private final VentaService ventaService;
    private final VentaRepository ventaRepository;

    public CocheService(CocheRepository cocheRepository, VentaService ventaService, VentaRepository ventaRepository) {
        this.cocheRepository = cocheRepository;
        this.ventaService = ventaService;
        this.ventaRepository = ventaRepository;
    }

    /**
     * Save a coche.
     *
     * @param coche the entity to save.
     * @return the persisted entity.
     */
    public Coche save(Coche coche) {
        log.debug("Request to save Coche : {}", coche);
        return cocheRepository.save(coche);
    }

    /**
     * Partially update a coche.
     *
     * @param coche the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Coche> partialUpdate(Coche coche) {
        log.debug("Request to partially update Coche : {}", coche);

        return cocheRepository
            .findById(coche.getId())
            .map(
                existingCoche -> {
                    if (coche.getMarca() != null) {
                        existingCoche.setMarca(coche.getMarca());
                    }
                    if (coche.getModelo() != null) {
                        existingCoche.setModelo(coche.getModelo());
                    }
                    if (coche.getPrecio() != null) {
                        existingCoche.setPrecio(coche.getPrecio());
                    }
                    if (coche.getVendido() != null) {
                        existingCoche.setVendido(coche.getVendido());
                    }

                    return existingCoche;
                }
            )
            .map(cocheRepository::save);
    }

    /**
     * Get all the coches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Coche> findAll(Pageable pageable) {
        log.debug("Request to get all Coches");
        return cocheRepository.findAll(pageable);
    }

    /**
     * Get one coche by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Coche> findOne(Long id) {
        log.debug("Request to get Coche : {}", id);
        return cocheRepository.findById(id);
    }

    /**
     * Delete the coche by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Coche : {}", id);

        Optional<Coche> c = this.cocheRepository.findById(id);
        Coche coche = c.get();
        cocheRepository.deleteById(id);

        Optional<Venta> v = this.ventaRepository.findById(coche.getVenta().getId());

        Venta venta2 = v.get();
        if (venta2 != null) {
            this.ventaRepository.deleteById(venta2.getId());
        }
    }

    public void cambiarValor(Venta venta) {
        Optional<Coche> coche = this.cocheRepository.findById(venta.getCoche());
        if (!coche.isPresent()) {} else {
            Coche c = coche.get();
            c.setVendido(true);
            c.setVenta(venta);
            //venta.setComprador(comprador);
            this.cocheRepository.save(c);
        }
    }

    public void actualizarValorAntes(Venta venta) {
        // Comprador comprador = venta.getComprador();
        Optional<Coche> c = this.cocheRepository.findById(venta.getCoche());

        Coche coche2 = c.get();
        coche2.setVendido(true);
        coche2.setVenta(venta);
        // comprador.setCocheComprado(coche2.getId());
        // this.compradorRepository.save(comprador);
        this.cocheRepository.save(coche2);
    }

    public void actualizarValorDespues(Venta venta) {
        // Comprador comprador = venta.getComprador();
        Optional<Coche> c = this.cocheRepository.findById(venta.getCoche());
        Coche coche = c.get();
        coche.setVendido(false);
        coche.setVenta(null);

        // comprador.setCocheComprado(null);
        // this.compradorRepository.save(comprador);
        this.cocheRepository.save(coche);
    }

    public void eliminarValor(Venta venta) {
        // Comprador comprador = venta.getComprador();
        // comprador.setCocheComprado(null);
        // this.compradorRepository.save(comprador);
        Optional<Coche> c = this.cocheRepository.findById(venta.getCoche());
        if (!c.isPresent()) {} else {
            Coche coche = c.get();
            coche.setVenta(null);
            coche.setVendido(false);
            this.cocheRepository.save(coche);
        }
    }
}
