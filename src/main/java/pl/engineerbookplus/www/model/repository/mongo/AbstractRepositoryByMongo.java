package pl.engineerbookplus.www.model.repository.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import pl.engineerbookplus.www.model.repository.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractRepositoryByMongo<T extends Object>
        implements Repository<T> {

    @Autowired
    protected MongoOperations mongo;
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
        mongo.save(t);
        return t;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T searchRecordById(Serializable id) {
        return (T) mongo.findById(id, getDomainClass());
    }

    @Override
    @SuppressWarnings("unchecked")
    public T loadRecordById(Serializable id) {
        return searchRecordById(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAllRecords() {
        return mongo.findAll(getDomainClass());
    }

    @Override
    public T editRecord(T t) {
        mongo.save(t);
        return t;
    }

    @Override
    public T eraseRecord(T t) {
        mongo.remove(t);
        return t;
    }

    @Override
    public T eraseRecordById(Serializable id) {
        return eraseRecord(loadRecordById(id));
    }

    @Override
    public int eraseAllRecords() {
        mongo.dropCollection(getDomainClass());
        return 1;
    }

    @Override
    public long numberOfRecords() {
        return (long) mongo.findAll(getDomainClass()).size();
    }

    @Override
    public boolean isExistRecordById(Serializable id) {
        return (searchRecordById(id) != null);
    }

}
