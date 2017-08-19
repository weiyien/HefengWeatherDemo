package zn.iot.xin.hefengweatherdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import zn.iot.xin.hefengweatherdemo.common.AppContext;
import zn.iot.xin.hefengweatherdemo.weather.WeatherUtil;

public class MainActivity extends AppCompatActivity {
    private TextView hello;
    private Button button;
    private HttpUtil httpUtil;
    private String url;
    private WeatherUtil weatherUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello= (TextView) findViewById(R.id.hello);
        button= (Button) findViewById(R.id.button);
        httpUtil=new HttpUtil();
        weatherUtil=new WeatherUtil();
        url= AppContext.weather_url+"city=sanming&key="+AppContext.weather_key;//更改后才可以用
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpUtil.get(url, null, new HttpUtil.HttpCallback() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            String msg="";
                            weatherUtil.setJson(data);
                            if(weatherUtil.getStatus()){
                                msg=weatherUtil.getAqiCity().toString();
                                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                                msg=weatherUtil.getNow().toString();
                                hello.setText(msg);
                            }else{
                                Toast.makeText(getApplicationContext(),"error,请检查",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(getApplicationContext(),"网络错误",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
