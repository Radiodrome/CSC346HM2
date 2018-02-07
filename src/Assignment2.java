import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Michael Brown
 * CSC346
 * Homework2
 */
public class Assignment2 {
    static Connection conn;
    static Statement st;
    static ResultSet rs;
    public static void main(String[] args){
        //connection details
        String host = "jdbc:mysql://turing.cs.missouriwestern.edu/misc";
        String user = "csc254";
        String password = "age126";

        //reading in zip code and radius input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a zip code to center on: ");
        int z = input.nextInt();
        System.out.print("Enter the radius to search in miles: ");
        double r = input.nextDouble() * 1.60934; //radius is entered in miles and immediately converted to kilometers
        input.close();

        //probably should've done this in a separate class and methods to avoid this ugly main
        try {
            //making connection
            conn = DriverManager.getConnection(host,user,password);
            String originQuery = String.format("select zipcode, city, state, lat, `long`, estimatedpopulation from zips2 " +
                    "WHERE (zipcode = %d) and locationType = \"PRIMARY\"", z);
            st = conn.createStatement();
            rs = st.executeQuery(originQuery);

            //finding the disaster origin by the provided zipcode
            Place origin = null;
            ArrayList<Place> places = new ArrayList<>();
            while(rs.next()){
                int zip = rs.getInt("zipcode");
                String city = rs.getString("city");
                String state = rs.getString("state");
                double lat = rs.getDouble("lat");
                double lon = rs.getDouble("long");
                int pop = rs.getInt("estimatedpopulation");
                origin = new Place(zip,city,state,lat,lon,pop, 0.0,0.0);
                places.add(origin);
            }

            //executing second query to find nearby cities
            if(origin != null) {
                String fullQuery = "SELECT zipcode, city, state, lat, `long`, estimatedpopulation from zips2 WHERE locationType = \"Primary\"";
                rs = st.executeQuery(fullQuery);

                //filtering through the result set to include only locations within the specified distance
                while (rs.next()) {
                    int zip = rs.getInt("zipcode");
                    String city = rs.getString("city");
                    String state = rs.getString("state");
                    double lat = rs.getDouble("lat");
                    double lon = rs.getDouble("long");
                    int pop = rs.getInt("estimatedpopulation");
                    double dist = haversine(origin.getLat(), origin.getLon(), lat, lon);
                    if (dist < r && zip != origin.getZipcode()) {
                        places.add(new Place(zip,city,state,lat,lon,pop,dist, dist*.621371));
                    }
                }

                //remove duplicate entries in a really cringe-inducing way
                for(int i =0; i<places.size();i++){
                    for(int j=i+1; j<places.size();j++){
                        Place firstPlace = places.get(i);
                        Place secondPlace = places.get(j);
                        if((firstPlace.getCity().equals(secondPlace.getCity())) && (firstPlace.getState().equals(secondPlace.getState()))){
                            firstPlace.setPop(firstPlace.getPop()+secondPlace.getPop());
                            places.remove(secondPlace);
                            j--;
                        }
                    }
                }

                //print locations
                for(Place p : places){
                    System.out.println(p);
                }
            }

            //close connections
            st.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //courtesy of rosetta code
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
