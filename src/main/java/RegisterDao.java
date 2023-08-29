import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDao {
    static String dburl = "jdbc:mysql://localhost:3306/test";
    static String dbuname = "root";
    static String dbpassword = "password";
    static String dbdriver = "com.mysql.cj.jdbc.Driver";
    static Connection con = null;

    public void loadDriver(String dbDriver) throws ClassNotFoundException {
        Class.forName(dbDriver);
    }

    public static void main(String args[]) {
        try {
            con = DriverManager.getConnection(dburl, dbuname, dbpassword);
            System.out.println(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String insert_school_id(String id) {
        try {
            loadDriver(dbdriver);
            con = DriverManager.getConnection(dburl, dbuname, dbpassword);
            
            String sql = "insert into school_id values('" + id + "', 0)";
            try {
                Statement statement = con.createStatement();
                int rowsAffected = statement.executeUpdate(sql);
                statement.close();
                
                if (rowsAffected > 0) {
                    return "Data Entered Successfully";
                } else {
                    return "Data Not Entered Successfully";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "Data Not Entered Successfully";
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "Data Not Entered Successfully";
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
