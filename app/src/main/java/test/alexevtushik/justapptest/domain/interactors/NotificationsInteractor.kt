package test.alexevtushik.justapptest.domain.interactors

import io.reactivex.Single
import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel

/**
 * Interface defining methods of business logic for Notifications feature
 */
interface NotificationsInteractor {

    fun getNotifications() : Single<List<NotificationModel>>

    fun getNotification(notificationId: String) : Single<NotificationModel>
}