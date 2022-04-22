package ru.myitschool.lessonmovie;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    public static MovieApi api;
    private final String BASE_URL = "https://api.themoviedb.org/3/";
    private final String API_KEY = "2e774b038b2dc15a1db7397f1b6b63a7";
    public final static String IMAGE_URL = "https://themoviedb.org/t/p/w300";

    @Override
    public void onCreate() {
        super.onCreate();
        api = createRetrofit().create(MovieApi.class);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(getClient())
                .build();
    }

    private Gson getGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
    }

    private OkHttpClient getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(new Cache(getApplicationContext().getCacheDir(), 10L * 1024 * 1024));
        Interceptor interceptor = chain -> {
            Request request = chain.request();
            HttpUrl newUrl = request
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build();
            return chain.proceed(request.newBuilder().url(newUrl).build());
        };
        return client.addInterceptor(interceptor).build();
    }

}
