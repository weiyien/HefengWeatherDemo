package zn.iot.xin.hefengweatherdemo.weather;

/**
 * Created by dim on 2017/8/19.
 */

public class City {
    private String aqi;
    private String pm10;
    private String pm25;
    private String qlty;

    public City() {
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    @Override
    public String toString() {
        return "City{" +
                "aqi='" + aqi + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", qlty='" + qlty + '\'' +
                '}';
    }
}
