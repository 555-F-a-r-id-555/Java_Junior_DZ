package org.example.DZ3.TestConnection;

import java.sql.*;

public class TestConnect {
    // JDBC Java DataBase Connectivity - набор классов и интерфейсов (в JDK), с помощью которых
    // можно организовать взаимодействие с реляционными БД

    // java.sql.Driver - интерфейс, в котором прописано взаимодействие с БД
    // org.postgresql.Driver, oracle.Driver, org.h2.Driver, ...mysql.Driver, ...

    // DriverManager - класс, в котором зарегистрированы все драйверы

    // Connection - интерфейс, отвечающий за соединение
    // Statement - интерфейс, отвечающий за запрос в сторону БД
    // PreparedStatement - расширяет возможности Statement и позволяет избежать sql-инъекций

    // jdbc:postgres:user@password:host:port///

    public static void main(String[] args) throws SQLException {
        // URL для подключения к базе данных H2 в памяти
        String jdbcURL = "jdbc:h2:mem:test";
        String username = "sa";
        String password = "123";

        // Подключение к базе данных
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            System.out.println("Подключение установлено.");


            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE example (id INT PRIMARY KEY, name VARCHAR(255))");
                statement.execute("INSERT INTO example (id, name) VALUES (1, 'Alice')");
                statement.execute("INSERT INTO example (id, name) VALUES (2, 'Bob')");


                ResultSet resultSet = statement.executeQuery("SELECT * FROM example");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.println("ID: " + id + ", Name: " + name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
