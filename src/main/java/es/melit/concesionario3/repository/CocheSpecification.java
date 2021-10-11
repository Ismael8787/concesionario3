package es.melit.concesionario3.repository;

import es.melit.concesionario3.domain.Coche;
import es.melit.concesionario3.domain.Coche_;
import es.melit.concesionario3.service.dto.CriteriaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CocheSpecification extends JpaSpecificationExecutor<Coche> {
    public static Specification<Coche> searchingParam(CriteriaDTO Marca) {
        return new Specification<Coche>() {
            private static final long serialVersionUID = 1L;

            // public Specification<Coche> marcaLike(String name) {
            //     return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Coche_.MARCA), "%" + name + "%");
            // }

            // public Specification<Coche> modeloLike(String modelo) {
            //     return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Coche_.MODELO), "%" + modelo + "%");
            // }

            public Predicate toPredicate(Root<Coche> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> ors = new ArrayList<Predicate>();
                Expression<String> marca = root.get("marca").as(String.class);
                Expression<String> modelo = root.get("modelo").as(String.class);

                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(builder.like(marca, "%" + Marca.getArray(0) + "%"));
                if (Marca.buscarLongitud() > 1) {
                    predicates.add(builder.like(modelo, "%" + Marca.getArray(1) + "%"));
                }
                ors.add(builder.or(predicates.toArray(new Predicate[] {})));
                Predicate result = builder.or(ors.toArray(new Predicate[] {}));
                return result;
            }
            // // Specification<Coche> specification = Specification.where(null);
            // if (Marca != null) {
            //     if (Marca.getArray(0) != null) {
            //         specification = specification.and( marcaLike(Marca.getArray(0)));
            //     }
            //     // if (Marca.getArray(1) != null) {
            //     //     specification = specification.and(modeloLike(Marca.getArray(1)));
            //     // }
            // }

            // return (Predicate) specification;

        };
    }
}
