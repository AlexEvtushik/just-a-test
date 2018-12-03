package test.alexevtushik.justapptest.models.mappers

import test.alexevtushik.justapptest.models.notifications.network.*
import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel

class NotificationMapper : Mapper<BaseNotification, NotificationModel>() {

    override fun transform(from: BaseNotification): NotificationModel {
        val notification = NotificationModel()
        notification.id = from.id
        notification.date = from.date
        notification.type = from.getType()
        if (from is ImprovePerformanceNotification) {
            notification.peopleViewedCount = from.peopleViewedCount
        }
        when (from) {
            is AbsentPersonNotification -> notification.profile = from.profile
            is FavoriteNotification -> notification.profile = from.profile
            is NewContactNotification -> notification.profile = from.profile
        }
        return notification
    }
}