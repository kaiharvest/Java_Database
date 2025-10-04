package indra.dwi.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {

    @Test
    void testCreateStatement()  throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        connection.close();
        statement.close();
    }

    @Test
    void testExecuteUpdate() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
            INSERT INTO customers (id, name, email)
            VALUES ('prabowo', 'Prabowo', 'prabowo@example.com')
        """;

        int update = statement.executeUpdate(sql);
        System.out.println("Jumlah row yang ditambahkan: " + update);

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteQuery() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
            SELECT * FROM customers
        """;

        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.close();
        statement.close();
        connection.close();
    }

}
