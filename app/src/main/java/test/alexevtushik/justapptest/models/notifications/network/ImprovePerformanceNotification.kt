package test.alexevtushik.justapptest.models.notifications.network

import com.github.jasminb.jsonapi.annotations.Type

@Type("improve-perfomance-notifications")
class ImprovePerformanceNotification : BaseNotification() {

    var peopleViewedCount: Int? = null

    override fun getType(): NotificationType {
        return NotificationType.IMPROVE_PERFORMANCE
    }
}