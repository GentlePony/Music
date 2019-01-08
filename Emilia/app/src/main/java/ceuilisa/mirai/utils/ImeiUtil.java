package ceuilisa.mirai.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImeiUtil {

    public static final String getIMEI(Context context) {
        try {
            //实例化TelephonyManager对象
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMEI号
            String imei = telephonyManager.getDeviceId();
            //在次做个验证，也不是什么时候都能获取到的啊
            if (imei == null) {
                imei = "";
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }


    public static void login(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        //2构造Request,
        //builder.get()代表的是get请求，url方法里面放的参数是一个网络地址
        Request.Builder builder = new Request.Builder();
        String fullUrl = "https://pixivgo.foxzs.cn/user/IMEI/" + getIMEI(context) + ".json";
        Request request = builder.get().url(fullUrl).build();

        //3将Request封装成call
        Call call = okHttpClient.newCall(request);

        //4，执行call，这个方法是异步请求数据
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败调用
                Log.e("MainActivity", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //成功调用
                Log.e("MainActivity", "onResponse: ");
                //获取网络访问返回的字符串
                String backString = response.body().string();
                if (backString.contains("ok")) {
                    Toast.makeText(context, "你是付费用户", Toast.LENGTH_SHORT).show();
                } else if (backString.contains("error")) {
                    Toast.makeText(context, "你是白嫖用户", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
