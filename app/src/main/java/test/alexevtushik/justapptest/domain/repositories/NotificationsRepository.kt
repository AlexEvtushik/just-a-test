package test.alexevtushik.justapptest.domain.repositories

import io.reactivex.Single
import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel

/**
 * Interface defining methods for the retrieving of Notifications from data sources
 */
interface NotificationsRepository {

    fun loadNotifications() : Single<List<NotificationModel>>

    fun loadNotification(notificationId: String) : Single<NotificationModel>
}