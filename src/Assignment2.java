import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by brown on 2/4/2018.
 */
public class Assignment2 {
    public static void main(String[] args){
        String host = "jdbc:mysql://turing.cs.missouriwestern.edu/misc";
        String user = "csc254";
        String password = "age126";

        Connection conn;
        Statement st;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(host,user,password);
            String query = "select * from zips";
            st = (Statement) conn.createStatement();
            rs = st.executeQuery(query);

            while(rs.next()){
                System.out.println(rs.getString("zip_code"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
