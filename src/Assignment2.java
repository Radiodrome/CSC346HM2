import java.sql.*;
import java.util.ArrayList;
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
        System.out.print("Enter a zip code to center on: ");
        int z = reader.nextInt(); // Scans the next token of the input as an int.
        System.out.print("Enter the radius to search in miles: ");
        double r = reader.nextDouble() * 1.60934;
        //once finished
        reader.close();

        try {
            conn = DriverManager.getConnection(host,user,password);
            String originQuery = String.format("select zipcode, city, state, lat, `long`, estimatedpopulation from zips2 " +
                    "WHERE (zipcode = %d) and locationType = \"PRIMARY\"", z);
            st = conn.createStatement();
            rs = st.executeQuery(originQuery);

            Place origin = null;
            ArrayList<Place> places = new ArrayList<>();
            while(rs.next()){
                int zip = rs.getInt("zipcode");
                String city = rs.getString("city");
                String state = rs.getString("state");
                double lat = rs.getDouble("lat");
                double lon = rs.getDouble("long");
                int pop = rs.getInt("estimatedpopulation");
                origin = new Place(zip,city,state,lat,lon,pop, 0);
                places.add(origin);
                System.out.println(origin);
            }

            if(origin != null) {
                String fullQuery = "SELECT zipcode, city, state, lat, `long`, estimatedpopulation from zips2 WHERE locationType = \"Primary\"";
                rs = st.executeQuery(fullQuery);

                while (rs.next()) {
                    int zip = rs.getInt("zipcode");
                    String city = rs.getString("city");
                    String state = rs.getString("state");
                    double lat = rs.getDouble("lat");
                    double lon = rs.getDouble("long");
                    int pop = rs.getInt("estimatedpopulation");
                    double dist = haversine(origin.getLat(), origin.getLon(), lat, lon);
                    if (dist < r && zip != origin.getZipcode()) {
                        places.add(new Place(zip,city,state,lat,lon,pop,dist));
                    }
                }

                for(Place p : places){
                    System.out.println(p);
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}
