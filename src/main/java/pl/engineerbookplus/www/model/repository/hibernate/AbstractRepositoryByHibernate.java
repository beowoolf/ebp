package pl.engineerbookplus.www.model.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.engineerbookplus.www.model.repository.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional(transactionManager = "transactionManagerByHibernate")
public abstract class AbstractRepositoryByHibernate<T extends Object>
        implements Repository<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> domainClass;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

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
        getSession().save(t);
        return t;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T searchRecordById(Serializable id) {
        return (T) getSession().get(getDomainClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T loadRecordById(Serializable id) {
        return (T) getSession().load(getDomainClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAllRecords() {
        return getSession().createQuery("from " + getDomainClassName()).list();
    }

    @Override
    public T editRecord(T t) {
        getSession().update(t);
        return t;
    }

    @Override
    public T eraseRecord(T t) {
        getSession().delete(t);
        return t;
    }

    @Override
    public T eraseRecordById(Serializable id) {
        return eraseRecord(loadRecordById(id));
    }

    @Override
    public int eraseAllRecords() {
        return getSession().createQuery("delete " + getDomainClassName())
                .executeUpdate();
    }

    @Override
    public long numberOfRecords() {
        return (long) getSession()
                .createQuery("select count(*) from " + getDomainClassName())
                .uniqueResult();
    }

    @Override
    public boolean isExistRecordById(Serializable id) {
        return (searchRecordById(id) != null);
    }

}
