package indra.dwi.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetaDataTest {

    @Test
    void testDatabaseMetaData() throws Exception {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());

        ResultSet belajarJavaDatabase = databaseMetaData.getTables("belajar_java_database", null, null, null);

        while (belajarJavaDatabase.next()) {
            String tableName = belajarJavaDatabase.getString("TABLE_NAME");
            System.out.println("Table " + tableName);
        }

        connection.close();
    }

    @Test
    void testParameterMetaDAta() throws Exception {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

        System.out.println(parameterMetaData.getParameterCount());

        preparedStatement.close();
        connection.close();
    }

}
