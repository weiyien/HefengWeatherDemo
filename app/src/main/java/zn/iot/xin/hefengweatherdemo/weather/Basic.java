package zn.iot.xin.hefengweatherdemo.weather;

/**
 * Created by dim on 2017/8/19.
 */

public class Basic {
    private String city;
    private String cnty;
    private String id;
    private String lat;
    private String lon;
    private String update_loc;
    private String update_utc;

    public Basic() {
    }

    public Basic(String city, String cnty, String id, String lat, String lon, String update_loc, String update_utc) {
        this.city = city;
        this.cnty = cnty;
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.update_loc = update_loc;
        this.update_utc = update_utc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getUpdate_loc() {
        return update_loc;
    }

    public void setUpdate_loc(String update_loc) {
        this.update_loc = update_loc;
    }

    public String getUpdate_utc() {
        return update_utc;
    }

    public void setUpdate_utc(String update_utc) {
        this.update_utc = update_utc;
    }
}
