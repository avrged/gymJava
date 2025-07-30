package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mariadb://localhost:3306/gym";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static DatabaseConfig instance;
    private Connection connection;

    private DatabaseConfig() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error conectando a la base de datos: " + e.getMessage(), e);
        }
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo conexión a la base de datos: " + e.getMessage(), e);
        }
        return connection;
    }
}
