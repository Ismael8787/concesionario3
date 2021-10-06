package es.melit.concesionario3.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Venta.
 */
@Entity
@Table(name = "venta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @NotNull
    @Column(name = "num_factura", nullable = false)
    private String numFactura;

    @NotNull
    @Column(name = "precio_total", nullable = false)
    private Double precioTotal;

    @Column(name = "comprador")
    private String comprador;

    @ManyToOne
    private User vendedorId;

    @Column(name = "coche")
    private Long coche;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venta id(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public Venta fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNumFactura() {
        return this.numFactura;
    }

    public Venta numFactura(String numFactura) {
        this.numFactura = numFactura;
        return this;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public Double getPrecioTotal() {
        return this.precioTotal;
    }

    public Venta precioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
        return this;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public User getVendedorId() {
        return this.vendedorId;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public Venta vendedorId(User user) {
        this.setVendedorId(user);
        return this;
    }

    public void setVendedorId(User user) {
        this.vendedorId = user;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Venta)) {
            return false;
        }
        return id != null && id.equals(((Venta) o).id);
    }

    public Long getCoche() {
        return coche;
    }

    public void setCoche(Long coche) {
        this.coche = coche;
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Venta{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", numFactura='" + getNumFactura() + "'" +
            ", precioTotal=" + getPrecioTotal() +
            "}";
    }
}
