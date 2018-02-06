import java.sql.*;
import java.util.Scanner;

/**
 * Created by brown on 2/4/2018.
 */
public class Assignment2 {
    static Connection conn;
    static Statement st;
    static ResultSet rs;
    public static void main(String[] args){
        String host = "jdbc:mysql://turing.cs.missouriwestern.edu/misc";
        String user = "csc254";
        String password = "age126";

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a zipcode: ");
        int z = reader.nextInt(); // Scans the next token of the input as an int.
        System.out.println("Enter a radius: ");
        int r = reader.nextInt();
        //once finished
        reader.close();

        try {
            conn = DriverManager.getConnection(host,user,password);
            String query = String.format("select zipcode, city, state, lat, `long`, estimatedpopulation from zips2 WHERE (zipcode > %d)", z);
            st = conn.createStatement();
            rs = st.executeQuery(query);

            ResultSetMetaData rsMD = rs.getMetaData();
            int colCount = rsMD.getColumnCount();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
