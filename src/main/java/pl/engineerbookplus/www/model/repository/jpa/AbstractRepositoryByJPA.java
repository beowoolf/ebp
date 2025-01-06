package pl.engineerbookplus.www.model.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import pl.engineerbookplus.www.model.repository.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional(transactionManager = "transactionManagerByJPA")
public abstract class AbstractRepositoryByJPA<T extends Object>
        implements Repository<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> domainClass;

    @SuppressWarnings("unchecked")
    private Class<T> getDomainClass() {
        if (domainClass == null) {
            ParameterizedType thisType
                    = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class<T>) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    private String getDomainClassName() {
        return getDomainClass().getName();
    }

    @Override
    public T addRecord(T t) {
        entityManager.persist(t);
        return t;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T searchRecordById(Serializable id) {
        return (T) entityManager.find(getDomainClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T loadRecordById(Serializable id) {
        return searchRecordById(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAllRecords() {
        return entityManager.createQuery("from " + getDomainClassName())
                .getResultList();
    }

    @Override
    public T editRecord(T t) {
        entityManager.merge(t);
        return t;
    }

    @Override
    public T eraseRecord(T t) {
        entityManager.remove(t);
        return t;
    }

    @Override
    public T eraseRecordById(Serializable id) {
        return eraseRecord(loadRecordById(id));
    }

    @Override
    public int eraseAllRecords() {
        return entityManager.createQuery("delete from " + getDomainClassName())
                .executeUpdate();
    }

    @Override
    public long numberOfRecords() {
        return (long) entityManager
                .createQuery("select count(*) from " + getDomainClassName())
                .getSingleResult();
    }

    @Override
    public boolean isExistRecordById(Serializable id) {
        return (searchRecordById(id) != null);
    }

}
