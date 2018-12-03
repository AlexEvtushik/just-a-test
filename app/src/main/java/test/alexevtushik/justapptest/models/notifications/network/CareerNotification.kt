package test.alexevtushik.justapptest.models.notifications.network

import com.github.jasminb.jsonapi.annotations.Type

@Type("career-confirmed-notifications")
class CareerNotification : BaseNotification() {

    override fun getType(): NotificationType {
        return NotificationType.CAREER
    }
}