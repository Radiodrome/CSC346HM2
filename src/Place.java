/**
 * Created by brown on 2/5/2018.
 */
public class Place {
    int zipcode;
    String city;
    String state;
    double lat;
    double lon;
    int pop;
    double distKilo;
    double distMiles;

    public Place(int zipcode, String city, String state, double lat, double lon, int pop, double distKilo, double distMiles) {
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.lat = lat;
        this.lon = lon;
        this.pop = pop;
        this.distKilo = distKilo;
        this.distMiles = distMiles;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public double getDistKilo() {
        return distKilo;
    }

    public void setDistKilo(double distKilo) {
        this.distKilo = distKilo;
    }

    public double getDistMiles() {
        return distMiles;
    }

    public void setDistMiles(double distMiles) {
        this.distMiles = distMiles;
    }

    @Override
    public String toString() {
        return String.format("Zipcode:  %-6d | City:  %-20s | State:  %-4s | Lat:  %-3.2f | Lon:  %-3.2f | Pop:  %-8d | Dist(km):  %-6.2f | Dist(miles)  %-6.2f",
                zipcode,city,state,lat,lon,pop,distKilo,distMiles);
    }
}
