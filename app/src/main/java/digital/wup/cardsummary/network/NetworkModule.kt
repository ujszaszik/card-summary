package digital.wup.cardsummary.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    companion object {
        private const val CARDS_API_BASE_URL =
            "https://raw.githubusercontent.com/wupdigital/interview-api/master/api/v1/"
    }

    @Provides
    fun provideCountriesRetrofit(
        converterFactory: Converter.Factory,
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CARDS_API_BASE_URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    fun provideRxJavaCallAdapter(): RxJava3CallAdapterFactory = RxJava3CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideCardsService(retrofit: Retrofit): CardService =
        retrofit.create(CardService::class.java)

    @Provides
    fun provideKotlinSerializationConverter(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return Json.asConverterFactory(contentType)
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideNetworkRequest(): NetworkRequest = NetworkRequest.Builder().build()
}