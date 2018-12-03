package test.alexevtushik.justapptest.data.repositories

import com.github.jasminb.jsonapi.JSONAPIDocument
import io.reactivex.Scheduler
import io.reactivex.Single
import test.alexevtushik.justapptest.data.network.NotificationsApiService
import test.alexevtushik.justapptest.domain.repositories.NotificationsRepository
import test.alexevtushik.justapptest.models.mappers.NotificationMapper
import test.alexevtushik.justapptest.models.notifications.network.BaseNotification
import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel

class NotificationsRepositoryImpl(
    private val notificationsApi: NotificationsApiService,
    private val notificationsMapper: NotificationMapper,
    private val backgroundScheduler: Scheduler
) : NotificationsRepository {

    override fun loadNotifications(): Single<List<NotificationModel>> {
        return notificationsApi.getNotifications()
            .map { jsonApiDocument -> notificationsMapper.transform(jsonApiDocument.get()) }
            .subscribeOn(backgroundScheduler)
    }

    override fun loadNotification(notificationId: String): Single<NotificationModel> {
        return notificationsApi.getNotifications()
            .map { jsonApiDocument: JSONAPIDocument<List<BaseNotification>> ->
                notificationsMapper.transform(
                    jsonApiDocument.get()
                )
            }
            .map { notifications: List<NotificationModel> ->
                val filteredNotifications =
                    notifications.filter { notificationModel -> notificationModel.id.equals(notificationId) }
                filteredNotifications.first()
            }
            .subscribeOn(backgroundScheduler)
    }
}