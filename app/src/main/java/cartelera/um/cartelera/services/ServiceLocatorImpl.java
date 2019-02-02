package cartelera.um.cartelera.services;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cartelera.um.cartelera.api.Settings;
import cartelera.um.cartelera.flow.FlowManager;
import cartelera.um.cartelera.flow.FlowManagerImpl;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Christian on 15/01/2019.
 */

public class ServiceLocatorImpl implements ServiceLocator {
    private static final int TIMEOUT = 20;
    protected Context mContext;

    public ServiceLocatorImpl(final Context context) {
        this.mContext = context;
    }

    @Override
    public FlowManager getFlowManager() {
        return new FlowManagerImpl();
    }

    @Override
    public Gson getGson() {
        Gson gson;
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .disableHtmlEscaping()
                .create();
        return gson;
    }

    @Override
    public GenreService getGenreService() {
        return createService(GenreService.class);
    }

    @Override
    public MovieService getMovieService() {
        return createService(MovieService.class);
    }

    @Override
    public AuthService getAuthService() {
        return createService(AuthService.class);
    }

    @Override
    public Scheduler getSchedulers() {
        return AndroidSchedulers.mainThread();
    }

    protected <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Settings.API.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    public OkHttpClient getClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        return client;
    }
}