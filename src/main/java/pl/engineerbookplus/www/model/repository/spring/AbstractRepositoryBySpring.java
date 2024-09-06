package pl.engineerbookplus.www.model.repository.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import pl.engineerbookplus.www.model.repository.Repository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractRepositoryBySpring<T extends Object>
        implements Repository<T> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    protected abstract PreparedStatementCreator addPreparedStatementCreator(final T t);

    protected abstract PreparedStatementCreator editPreparedStatementCreator(final T t);

    @Override
    public T addRecord(T t) {
        jdbcTemplate.update(addPreparedStatementCreator(t));
        return t;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T searchRecordById(Serializable id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @SuppressWarnings("unchecked")
    public T loadRecordById(Serializable id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAllRecords() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T editRecord(T t) {
        jdbcTemplate.update(editPreparedStatementCreator(t));
        return t;
    }

    @Override
    public T eraseRecord(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T eraseRecordById(Serializable id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eraseAllRecords() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long numberOfRecords() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isExistRecordById(Serializable id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
