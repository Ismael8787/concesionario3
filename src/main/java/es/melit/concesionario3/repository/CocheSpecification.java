package es.melit.concesionario3.repository;

import es.melit.concesionario3.domain.Coche;
import es.melit.concesionario3.domain.Coche_;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CocheSpecification extends JpaSpecificationExecutor<Coche> {
    public static Specification<Coche> searchingParam(String Marca) {
        return new Specification<Coche>() {
            private static final long serialVersionUID = 1L;

            public Predicate toPredicate(Root<Coche> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate equalPredicate = builder.equal(root.get(Coche_.MARCA), Marca);
                return equalPredicate;
                // // query.distinct(true);
                // List<Predicate> ors = new ArrayList<Predicate>();

                // Expression<String> titulo = root.get("titulo").as(String.class);
                // Expression<String> descripcion = root.get("descripcion").as(String.class);
                // Expression<String> contenido = root.get("contenido").as(String.class);
                // Expression<String> procedimiento = root.get("procedimiento").as(String.class);
                // Expression<String> fecha = root.get("fecha").as(String.class);
                // // Join<Comunicado, PerfilUsuario> responsable = root.join("responsable", JoinType.LEFT);

                // String[] searchParam = filter.split(" ");
                // for (int i = 0; i < searchParam.length; i++) {

                //   List<Predicate> predicates = new ArrayList<Predicate>();
                //   predicates.add(builder.like(titulo, "%" + searchParam[i] + "%"));
                //   predicates.add(builder.like(descripcion, "%" + searchParam[i] + "%"));
                //   predicates.add(builder.like(contenido, "%" + searchParam[i] + "%"));
                //   predicates.add(builder.like(procedimiento, "%" + searchParam[i] + "%"));
                //   predicates.add(builder.like(fecha, "%" + searchParam[i] + "%"));

                //   ors.add(builder.or(predicates.toArray(new Predicate[] {})));
                // }
                // Predicate result = builder.or(ors.toArray(new Predicate[] {}));
                // return result;
            }
        };
    }
}
