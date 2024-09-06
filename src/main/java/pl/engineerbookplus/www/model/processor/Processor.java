package pl.engineerbookplus.www.model.processor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Processor<T extends Object> {

    T process(ResultSet rs) throws SQLException;

}
