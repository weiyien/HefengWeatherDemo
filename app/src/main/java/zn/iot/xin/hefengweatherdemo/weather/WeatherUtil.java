package zn.iot.xin.hefengweatherdemo.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dim on 2017/8/19.
 */

public class WeatherUtil {
    private String json;
    private JSONObject object;
    private JSONArray heWeather5;
    private JSONObject now;
    public String getJson() {
        return json;
    }

    public void setJson(String json) throws JSONException {
        this.json = json;
        object=new JSONObject(json);
        heWeather5=object.getJSONArray("HeWeather5");
        now= (JSONObject) heWeather5.get(0);
    }

    public City getAqiCity() throws JSONException {
        JSONObject aqi=now.getJSONObject("aqi");
        JSONObject city=aqi.getJSONObject("city");
        City city1=new City();
        city1.setAqi(city.getString("aqi"));
        city1.setQlty(city.getString("qlty"));
        city1.setPm25(city.getString("pm25"));
        city1.setPm10(city.getString("pm10"));
        return city1;
    }

    public Basic getBasic() throws JSONException {

        JSONObject basic=now.getJSONObject("basic");
        String city=basic.getString("city");
        String cnty=basic.getString("cnty");
        String id=basic.getString("id");
        String lat=basic.getString("lat");
        String lon=basic.getString("lon");
        JSONObject update=basic.getJSONObject("update");
        String loc=update.getString("loc");
        String utc=update.getString("utc");
        Basic basic2=new Basic(city,cnty,id,lat,lon,loc,utc);
        return basic2;

    }

    public void getDaily_foreast(){

    }

    public void getHourly_forecast(){

    }

    public Now getNow() throws JSONException {
        JSONObject now1=now.getJSONObject("now");
        JSONObject cond=now1.getJSONObject("cond");
        String cond_code=cond.getString("code");
        String cond_txt=cond.getString("txt");
        String fl=now1.getString("fl");
        String hum=now1.getString("hum");
        String pcpn=now1.getString("pcpn");
        String pres=now1.getString("pres");
        String tmp=now1.getString("tmp");
        String vis=now1.getString("vis");
        JSONObject wind=now1.getJSONObject("wind");
        String deg=wind.getString("deg");
        String dir=wind.getString("dir");
        String sc=wind.getString("sc");
        String spd=wind.getString("spd");
        Now now2=new Now(cond_code,cond_txt,fl,hum,pcpn,pres,tmp,vis,deg,dir,sc,spd);
        return now2;
    }
    public void getSuggestion(){

    }

    public boolean getStatus() throws JSONException {
        final String status = now.getString("status");
        if (!status.equals("ok")) {
            //    Toast.makeText(MainActivity.this, "获取实况天气失败！" + status, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
