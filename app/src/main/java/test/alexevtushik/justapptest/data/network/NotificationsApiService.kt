package test.alexevtushik.justapptest.data.network

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.jasminb.jsonapi.JSONAPIDocument
import com.github.jasminb.jsonapi.ResourceConverter
import com.github.jasminb.jsonapi.SerializationFeature
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import test.alexevtushik.justapptest.models.notifications.network.*


interface NotificationsApiService {

    @GET("notifications")
    fun getNotifications(): Single<JSONAPIDocument<List<BaseNotification>>>

    companion object {
        private var BASE_API: String = "https://private-anon-10bd661ccc-justtest11.apiary-mock.com/"

        fun create(): NotificationsApiService {
            val converter = ResourceConverter(
                ObjectMapper(),
                BaseNotification::class.java,
                FavoriteNotification::class.java,
                NewContactNotification::class.java,
                AbsentPersonNotification::class.java,
                CareerNotification::class.java,
                UpdateCharacteristicsNotifications::class.java,
                ImprovePerformanceNotification::class.java,
                Profile::class.java
            )
            converter.enableSerializationOption(SerializationFeature.INCLUDE_RELATIONSHIP_ATTRIBUTES)

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JSONAPIConverterFactory(converter))
                .baseUrl(BASE_API)
                .build()
            return retrofit.create(NotificationsApiService::class.java)
        }
    }
}