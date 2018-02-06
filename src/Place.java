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
    double distFromCenter;

    public Place(int zipcode, String city, String state, double lat, double lon, int pop) {
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.lat = lat;
        this.lon = lon;
        this.pop = pop;
        distFromCenter = -1.0;
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

    public double getDistFromCenter() {
        return distFromCenter;
    }

    public void setDistFromCenter(double distFromCenter) {
        this.distFromCenter = distFromCenter;
    }

    @Override
    public String toString() {
        return "Place{" +
                "zipcode=" + zipcode +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", pop=" + pop +
                ", distFromCenter=" + distFromCenter +
                '}';
    }
}
