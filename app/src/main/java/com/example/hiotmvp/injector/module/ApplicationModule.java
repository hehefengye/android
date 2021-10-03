package com.example.hiotmvp.injector.module;

import android.content.Context;

import com.example.hiotmvp.BuildConfig;
import com.example.hiotmvp.app.App;
import com.example.hiotmvp.data.MyService;
import com.example.hiotmvp.injector.ApplicationContext;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private App applicationContext;

    public ApplicationModule(App applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    @Singleton
    @ApplicationContext
    public Context providerContext() {
        return this.applicationContext;
    }

    @Provides
    @Singleton
    public Retrofit providerRetrofit(OkHttpClient client) {
        return  new Retrofit.Builder().baseUrl(MyService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient providerOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY:HttpLoggingInterceptor.Level.NONE);
       return new OkHttpClient.Builder().connectTimeout(6, TimeUnit.SECONDS)
               .addInterceptor(httpLoggingInterceptor)
               .cache(new Cache(new File(this.applicationContext.getCacheDir(), "HttpResponseCache"), 10 * 1024 *1024))
               .build();
    }

    @Provides
    @Singleton
    public MyService providerMyService(Retrofit retrofit) {
        return retrofit.create(MyService.class);
    }
}
