package pl.engineerbookplus.www.model.repository.spring.mapper;

import org.springframework.jdbc.core.RowMapper;
import pl.engineerbookplus.www.model.processor.Processor;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractMapper<T extends Object> implements RowMapper<T> {

    protected abstract Processor<T> getProcessor();

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        return getProcessor().process(rs);
    }

}
