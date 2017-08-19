package zn.iot.xin.hefengweatherdemo.common;

import android.app.Application;

public class AppContext extends Application {

    public static final String weather_key="yourkey";//获取地址https://console.heweather.com/my/service
    public static final String weather_url="https://free-api.heweather.com/v5/weather?";


    private static AppContext app;

    public AppContext() {
        app = this;
    }

    public static synchronized AppContext getInstance() {
        if (app == null) {
            app = new AppContext();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}