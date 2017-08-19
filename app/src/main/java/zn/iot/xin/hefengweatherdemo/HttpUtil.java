package zn.iot.xin.hefengweatherdemo;

/**
 * Created by dim on 2017/8/7.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zn.iot.xin.hefengweatherdemo.common.AppContext;


/**
 * okhttp请求
 * @author Flyjun
 *
 */
public class HttpUtil {
    private static final String UTF_8 = "UTF-8";
    public static String TAG="debug-okhttp";
    public static boolean isDebug=true;

    private OkHttpClient client;
    // 超时时间
    public static final int TIMEOUT = 1000;

    //json请求
    public static final MediaType JSON = MediaType
            .parse("application/json; charset=utf-8");

    private Handler handler = new Handler(Looper.getMainLooper());



    public HttpUtil() {
        // TODO Auto-generated constructor stub
        this.init();
    }

    private void init() {

        client = new OkHttpClient();

        // 设置超时时间
        client.newBuilder().connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS).build();

    }

    /**
     * post请求，json数据为body
     *
     * @param url
     * @param json
     * @param callback
     */
    public void postJson(String url, String json, final HttpCallback callback) {

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();

        onStart(callback);

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                onError(callback,e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    onSuccess(callback, response.body().string());
                } else {
                    onError(callback, response.message());
                }
            }

            public void onResponse(Response response) throws IOException {
                // TODO Auto-generated method stub

                if (response.isSuccessful()) {
                    onSuccess(callback, response.body().string());
                } else {
                    onError(callback, response.message());
                }
            }
            public void onFailure(Request arg0, IOException arg1) {
                // TODO Auto-generated method stub

                onError(callback, arg1.getMessage());
                arg1.printStackTrace();

            }
        });

    }

    /**
     * post请求 map为body
     *
     * @param url
     * @param map
     * @param callback
     */
    public void post(String url, Map<String, Object> map,final HttpCallback callback) {

        // FormBody.Builder builder = new FormBody.Builder();
        // FormBody body=new FormBody.Builder().add("key", "value").build();

        /**
         * 创建请求的参数body
         */
        FormBody.Builder builder = new FormBody.Builder();

        /**
         * 遍历key
         */
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {

                System.out.println("Key = " + entry.getKey() + ", Value = "
                        + entry.getValue());
                builder.add(entry.getKey(), entry.getValue().toString());

            }
        }

        RequestBody body = builder.build();

        Request request = new Request.Builder().url(url).post(body).build();

        onStart(callback);

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                onError(callback,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    onSuccess(callback, response.body().string());

                } else {
                    onError(callback, response.message());
                }
            }

            public void onResponse(final Response response) throws IOException {
                // TODO Auto-generated method stub

                if (response.isSuccessful()) {

                    onSuccess(callback, response.body().string());

                } else {
                    onError(callback, response.message());
                }
            }

            public void onFailure(Request arg0, IOException arg1) {
                // TODO Auto-generated method stub
                arg1.printStackTrace();
                onError(callback, arg1.getMessage());
            }
        });

    }

    /**
     * get请求
     * @param url
     * @param callback
     */
    public void get(String url, Map<String, String> param, final HttpCallback callback) {
      //  if(!checkNetwork())return;

        if(param != null && param.size() > 0) {
            url = url + "?" + mapToQueryString(param);
        }
        Request request = new Request.Builder().url(url).build();

        onStart(callback);

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                onError(callback,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    onSuccess(callback, response.body().string());
                } else {
                    onError(callback, response.message());
                }
            }


            public void onResponse(Response response) throws IOException {
                // TODO Auto-generated method stub
                if (response.isSuccessful()) {
                    onSuccess(callback, response.body().string());
                } else {
                    onError(callback, response.message());
                }
            }


            public void onFailure(Request arg0, IOException arg1) {
                // TODO Auto-generated method stub

                onError(callback, arg1.getMessage());
                arg1.printStackTrace();

            }
        });

    }

    /**
     * get请求
     * @param url
     * @param callback
     */
    public void get(String url,  final HttpCallback callback) {
        if(!checkNetwork())return;

        Request request = new Request.Builder().url(url).build();

        onStart(callback);

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                onError(callback,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    onSuccess(callback, response.body().string());
                } else {
                    onError(callback, response.message());
                }
            }


            public void onResponse(Response response) throws IOException {
                // TODO Auto-generated method stub
                if (response.isSuccessful()) {
                    onSuccess(callback, response.body().string());
                } else {
                    onError(callback, response.message());
                }
            }


            public void onFailure(Request arg0, IOException arg1) {
                // TODO Auto-generated method stub

                onError(callback, arg1.getMessage());
                arg1.printStackTrace();

            }
        });

    }

    /**
     * log信息打印
     * @param params
     */
    public void debug(String params){
        if(false == isDebug){
            return;
        }

        if(null == params){
            Log.d(TAG, "params is null");
        }else{
            Log.d(TAG, params);
        }
    }

    private void onStart(HttpCallback callback) {
        if (null != callback) {
            callback.onStart();
        }
    }

    private void onSuccess(final HttpCallback callback, final String data) {

        debug(data);

        if (null != callback) {
            handler.post(new Runnable() {
                public void run() {
                    // 需要在主线程的操作。
                    callback.onSuccess(data);
                }
            });
        }
    }

    private void onError(final HttpCallback callback,final String msg) {
        if (null != callback) {
            handler.post(new Runnable() {
                public void run() {
                    // 需要在主线程的操作。
                    callback.onError(msg);
                }
            });
        }
    }


    /**
     * http请求回调
     *
     * @author Flyjun
     *
     */
    public static abstract class HttpCallback {
        // 开始
        public void onStart() {};

        // 成功回调
        public abstract void onSuccess(String data);

        // 失败回调
        public abstract void onError(String msg);
    }
    public static boolean isNetworkAvailable() {
        Context context= AppContext.getInstance();
        if(context!=null) {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connManager != null) {
                /**
                 * 获取网络信息实体
                 * 由于从系统服务中获取数据属于进程间通信，基本类型外的数据必须实现Parcelable接口，
                 * NetworkInfo实现了Parcelable，获取到的activeNetInfo相当于服务中网络信息实体对象的一个副本（拷贝），
                 * 所以，不管系统网络服务中的实体对象是否置为了null，此处获得的activeNetInfo均不会发生变化
                 */
                NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
                if (activeNetInfo != null) {
                    return activeNetInfo != null && activeNetInfo.isAvailable() && activeNetInfo.isConnected();
                }
            }
        }
        return  false;
    }
    public static boolean checkNetwork(){
        if (!isNetworkAvailable()) {
            Toast.makeText(AppContext.getInstance(), "无网络连接", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public static String mapToQueryString(Map<String, String> map) {
        StringBuilder string = new StringBuilder();
        /*if(map.size() > 0) {
            string.append("?");
        }*/
        try {
            for(Map.Entry<String, String> entry : map.entrySet()) {
                string.append(entry.getKey());
                string.append("=");
                string.append(URLEncoder.encode(entry.getValue(), UTF_8));
                string.append("&");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string.toString();
    }
}