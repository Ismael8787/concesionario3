package es.melit.concesionario3.domain;

import es.melit.concesionario3.repository.CocheRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class CocheSpect {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Coche");

    // CriteriaBuilder criteriaBuilder = entityManagerFactory.getCriteriaBuilder();
    // CriteriaQuery<Coche> criteriaQuery = criteriaBuilder.createQuery(Coche.class);
    // Root<Coche> CocheRoot = criteriaQuery.from(Coche.class);
    // criteriaQuery.select(CocheRoot);

    public static Specification<Coche> getCochesByVendidoSpec(boolean vendido) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Coche_.vendido), vendido);
        };
    }

    public static List<Coche> findAllEmployees() {
        System.out.println("-- All employees --");
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Coche> query = cb.createQuery(Coche.class); //create query object
        Root<Coche> employeeRoot = query.from(Coche.class); //get object representing 'from' part
        query.select(employeeRoot).where(cb.equal(employeeRoot.get("vendido"), false)); //linking 'select' and 'from' parts, equivalent to 'select t from Employee t;'7

        TypedQuery<Coche> typedQuery = em.createQuery(query);
        List<Coche> coches = typedQuery.getResultList();

        return coches;
    }

    public static Specification<Coche> getCochesByPrecioSpec(Double precio) {
        return new Specification<Coche>() {
            @Override
            public Predicate toPredicate(Root<Coche> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate = criteriaBuilder.equal(root.get(Coche_.precio), precio);
                return equalPredicate;
            }
        };
    }
    // public static Specification<Coche> getCochesByPhoneTypeSpec(PhoneType phoneType) {
    //     return new Specification<Coche>() {
    //         @Override
    //         public Predicate toPredicate(Root<Coche> root,
    //                                      CriteriaQuery<?> query,
    //                                      CriteriaBuilder criteriaBuilder) {
    //             ListJoin<Coche, Phone> phoneJoin = root.join(Coche_.phones);
    //             Predicate equalPredicate = criteriaBuilder.equal(phoneJoin.get(Phone_.type), phoneType);
    //             return equalPredicate;
    //         }
    //     };
    // }
}
