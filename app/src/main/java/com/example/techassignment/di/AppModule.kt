package com.example.techassignment.di


import android.content.Context
import com.example.techassignment.base.BaseApplication
import com.example.techassignment.data.shared.DataManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun provideDataManager(@ApplicationContext context: Context): DataManager =
        (Contexts.getApplication(context) as BaseApplication).dataManager!!

    @Provides
    @Singleton
    fun provideOkHttpClient(
        dataManager: DataManager,
    ): OkHttpClient {


        val builder =   OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("App-Version", "7.7.0.0.0")
                    .header("Device-Type", "android")
                    .header("Platform", "FLAGSHIP")
                    .header("Authorization", "token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjgyNDA2LCJ1c2VyX3Bob25lIjoiOTY2NTEyMTIxMjM0In0.7Taumv67S0xEv2P5K8F9JdJMYAferY45ZuNghUXtPW4")
                    .header("Accept-Language", "${dataManager.lang}")
                    .build()
                chain.proceed(request)
            })

        return builder.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://staging.sary.to/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}