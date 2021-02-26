package ir.org.tavanesh.di

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ir.org.tavanesh.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout((30 * 1000), TimeUnit.MILLISECONDS)
            .writeTimeout((10 * 1000), TimeUnit.MILLISECONDS)
            .connectTimeout((10 * 1000), TimeUnit.MILLISECONDS)
            .build()
    } else OkHttpClient
        .Builder()
        .readTimeout((30 * 1000), TimeUnit.MILLISECONDS)
        .writeTimeout((10 * 1000), TimeUnit.MILLISECONDS)
        .connectTimeout((10 * 1000), TimeUnit.MILLISECONDS)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .build()

}