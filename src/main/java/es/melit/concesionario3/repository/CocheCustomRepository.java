package es.melit.concesionario3.repository;

import es.melit.concesionario3.domain.Coche;
import es.melit.concesionario3.domain.Coche_;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;

public class CocheCustomRepository implements CocheRepository {

    @Autowired
    EntityManager em;

    public List<Coche> findByMarca(String marca, String modelo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Coche> coche = cq.from(Coche.class);
        Predicate marcaPredicate = cb.equal(coche.get(Coche_.MARCA), marca);
        Predicate modeloPredicate = cb.equal(coche.get(Coche_.MODELO), modelo);
        cq.where(marcaPredicate, modeloPredicate);
        TypedQuery<Coche> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Coche> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Coche> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Coche> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Coche> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Coche> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Coche> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub

    }

    @Override
    public Coche getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Coche> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Coche> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Coche> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Coche> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Coche> findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Coche entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<? extends Coche> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Coche> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Coche> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Coche> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Coche> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<Coche> findOne(Specification<Coche> spec) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Coche> findAll(Specification<Coche> spec) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Coche> findAll(Specification<Coche> spec, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Coche> findAll(Specification<Coche> spec, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count(Specification<Coche> spec) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Coche> findByVendidoFalse() {
        // TODO Auto-generated method stub
        return null;
    }
}
