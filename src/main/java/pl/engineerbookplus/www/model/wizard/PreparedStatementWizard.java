package pl.engineerbookplus.www.model.wizard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementWizard<T extends Object> {

    PreparedStatement createPreparedStatement(Connection c, T t) throws SQLException;

}
