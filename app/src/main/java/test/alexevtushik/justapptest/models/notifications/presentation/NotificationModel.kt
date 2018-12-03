package test.alexevtushik.justapptest.models.notifications.presentation

import test.alexevtushik.justapptest.models.notifications.network.NotificationType
import test.alexevtushik.justapptest.models.notifications.network.Profile

class NotificationModel {

    var id: String? = null
    var date: String? = null
    var profile: Profile? = null
    var peopleViewedCount: Int? = null
    var type: NotificationType = NotificationType.UNKNOWN
}