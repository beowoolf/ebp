package pl.engineerbookplus.www.model.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.engineerbookplus.www.model.processor.Processor;
import pl.engineerbookplus.www.model.repository.Repository;
import pl.engineerbookplus.www.model.repository.jdbc.util.ConnectionSingleton;
import pl.engineerbookplus.www.model.wizard.PreparedStatementWizard;

import javax.sql.DataSource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepositoryByJDBC<T extends Object>
        implements Repository<T> {

    @Autowired
    @Qualifier(value = "dataSourceBySpring")
    private DataSource dataSource;
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

    protected abstract PreparedStatementWizard<T> addRecordPreparedStatementWizard(T t);

    protected abstract PreparedStatementWizard<T> editRecordPreparedStatementWizard(T t);

    protected abstract Processor<T> getProcessor();

    private T addOrEditRecord(T t, PreparedStatementWizard<T> pstu) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionSingleton.getConnection(dataSource);
            ps = pstu.createPreparedStatement(c, t);
            ps.execute();
            return t;
        } catch (SQLException ex) {
            return null;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    protected List<T> searchRecords(String sql) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> t = new ArrayList<>();
        try {
            c = ConnectionSingleton.getConnection(dataSource);
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            Processor<T> processor = getProcessor();
            while (rs.next())
                t.add(processor.process(rs));
            return t;
        } catch (SQLException ex) {
            return t;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public T addRecord(T t) {
        return addOrEditRecord(t, addRecordPreparedStatementWizard(t));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T searchRecordById(Serializable id) {
        List<T> t = searchRecords("select * from 16120792_nebp." + getDomainClassName().toLowerCase() + "s where id = " + id);
        if (t.isEmpty())
            return null;
        else
            return t.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T loadRecordById(Serializable id) {
        return searchRecordById(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAllRecords() {
        return searchRecords("select * from 16120792_nebp." + getDomainClassName().toLowerCase() + "s");
    }

    @Override
    public T editRecord(T t) {
        return addOrEditRecord(t, editRecordPreparedStatementWizard(t));
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
        return (searchRecordById(id) != null);
    }

}
