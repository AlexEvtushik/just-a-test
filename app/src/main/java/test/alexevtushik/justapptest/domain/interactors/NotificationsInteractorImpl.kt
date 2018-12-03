package test.alexevtushik.justapptest.domain.interactors

import io.reactivex.Single
import test.alexevtushik.justapptest.domain.repositories.NotificationsRepository
import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel

class NotificationsInteractorImpl(
    private val notificationsRepository: NotificationsRepository
) : NotificationsInteractor {

    override fun getNotifications(): Single<List<NotificationModel>> {
        return notificationsRepository.loadNotifications()
    }

    override fun getNotification(notificationId: String): Single<NotificationModel> {
        return notificationsRepository.loadNotification(notificationId)
    }
}