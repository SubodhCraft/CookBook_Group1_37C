package Database;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.ResultSet;

public interface Database {
    Connection openConnection();
    void closeConnection(Connection conn);
    ResultSet runQuery(Connection conn, String query);
    int executeUpdate(Connection conn, String query);
}
