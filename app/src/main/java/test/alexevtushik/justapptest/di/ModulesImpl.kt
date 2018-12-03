package test.alexevtushik.justapptest.di

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.jasminb.jsonapi.ResourceConverter
import com.github.jasminb.jsonapi.SerializationFeature
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import space.traversal.kapsule.Injects
import space.traversal.kapsule.required
import test.alexevtushik.justapptest.data.network.NotificationsApiService
import test.alexevtushik.justapptest.data.repositories.NotificationsRepositoryImpl
import test.alexevtushik.justapptest.domain.interactors.NotificationsInteractor
import test.alexevtushik.justapptest.domain.interactors.NotificationsInteractorImpl
import test.alexevtushik.justapptest.domain.repositories.NotificationsRepository
import test.alexevtushik.justapptest.models.mappers.NotificationMapper
import test.alexevtushik.justapptest.models.notifications.network.*

class RxModuleImpl : RxModule {

    override val backgroundScheduler: Scheduler
        get() = Schedulers.io()

    override val mainScheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}

class NetworkModuleImpl : NetworkModule {

    private val BASE_API: String = "https://private-anon-10bd661ccc-justtest11.apiary-mock.com/"

    override val notificationsApi: NotificationsApiService
        get() = create()

    private fun create(): NotificationsApiService {
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

class MappersModuleImpl : MappersModule {

    override val notificationsMapper: NotificationMapper
        get() = NotificationMapper()
}

class RepositoriesModuleImpl : RepositoriesModule, Injects<Module> {

    private val notificationsApi by required { notificationsApi }
    private val notificationsMapper by required { notificationsMapper }
    private val backgroundScheduler by required { backgroundScheduler }

    override val notificationsRepository: NotificationsRepository
        get() = NotificationsRepositoryImpl(notificationsApi, notificationsMapper, backgroundScheduler)

}

class InteractorsModuleImpl : InteractorsModule, Injects<Module> {

    private val notificationsRepository by required { notificationsRepository }

    override val notificationsInteractor: NotificationsInteractor
        get() = NotificationsInteractorImpl(notificationsRepository)
}