package yapp.com.railrotalk.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yapp.com.railrotalk.utils.URL;

/**
 * Created by HunJin on 2017-02-25.
 */

public class RestAPIBuilder {
    public static RestAPI buildRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.SERVER_URL)
                // Data converter
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        return retrofit.create(RestAPI.class);
    }

    public static RestAPI buildRetrofirServiceNode() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.NODE_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        return retrofit.create(RestAPI.class);
    }
}
