package com.adhishlal.mygithubprs.domain.di.base.module

import com.adhishlal.mygithubprs.BuildConfig
import com.adhishlal.mygithubprs.data.api.GitHubApiService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


@Module
class GitHubNetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(60, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(logging)
        httpClientBuilder.addInterceptor { chain ->
            val request: Request =
                chain.request().newBuilder().addHeader("Accept", "application/vnd.github+json")
                    .build()
            chain.proceed(request)
        }
        return httpClientBuilder.build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {


        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BaseURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideCheckEligibilityApiService(retrofit: Retrofit): GitHubApiService =
        retrofit.create(GitHubApiService::class.java)

    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }
}
