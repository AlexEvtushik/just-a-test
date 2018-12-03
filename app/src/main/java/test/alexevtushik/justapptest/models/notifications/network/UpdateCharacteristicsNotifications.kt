package test.alexevtushik.justapptest.models.notifications.network

import com.github.jasminb.jsonapi.annotations.Type

@Type("update-characteristics-notifications")
class UpdateCharacteristicsNotifications : BaseNotification() {

    override fun getType(): NotificationType {
        return NotificationType.UPDATE_CHARACTERISTICS
    }
}