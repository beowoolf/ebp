package pl.engineerbookplus.www.model.repository.spring.extractor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import pl.engineerbookplus.www.model.processor.Processor;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractExtractor<T extends Object> implements ResultSetExtractor<T> {

    protected abstract Processor<T> getProcessor();

    @Override
    public T extractData(ResultSet rs) throws SQLException, DataAccessException {
        if (rs.next())
            return getProcessor().process(rs);
        return null;
    }

}
