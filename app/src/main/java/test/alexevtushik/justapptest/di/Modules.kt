package test.alexevtushik.justapptest.di

import io.reactivex.Scheduler
import space.traversal.kapsule.HasModules
import test.alexevtushik.justapptest.data.network.NotificationsApiService
import test.alexevtushik.justapptest.domain.interactors.NotificationsInteractor
import test.alexevtushik.justapptest.domain.repositories.NotificationsRepository
import test.alexevtushik.justapptest.models.mappers.NotificationMapper

class Module(
    private val rxModule: RxModule,
    private val networkModule: NetworkModule,
    private val mappersModule: MappersModule,
    private val repositoriesModule: RepositoriesModule,
    private val interactorsModule: InteractorsModule
) :
    RxModule by rxModule,
    NetworkModule by networkModule,
    MappersModule by mappersModule,
    RepositoriesModule by repositoriesModule,
    InteractorsModule by interactorsModule,
    HasModules {

    override val modules: Set<Any>
        get() = setOf(rxModule, networkModule, repositoriesModule, interactorsModule)
}

interface RxModule {

    val backgroundScheduler: Scheduler

    val mainScheduler: Scheduler
}

interface NetworkModule {

    val notificationsApi: NotificationsApiService
}

interface MappersModule {

    val notificationsMapper: NotificationMapper
}

interface RepositoriesModule {

    val notificationsRepository: NotificationsRepository
}

interface InteractorsModule {

    val notificationsInteractor: NotificationsInteractor
}