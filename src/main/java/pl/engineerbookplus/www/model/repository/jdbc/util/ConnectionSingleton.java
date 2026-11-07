package pl.engineerbookplus.www.model.repository.jdbc.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSingleton {

    private volatile static Connection connection;

    private ConnectionSingleton() {
    }

    public static Connection getConnection(DataSource dataSource) throws SQLException {
        if (connection == null)
            synchronized (ConnectionSingleton.class) {
                if (connection == null)
                    connection = dataSource.getConnection();
            }
        return connection;
    }

}
