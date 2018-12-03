package test.alexevtushik.justapptest.models.notifications.network

import com.github.jasminb.jsonapi.annotations.Relationship
import com.github.jasminb.jsonapi.annotations.Type

@Type("absent-notifications")
class AbsentPersonNotification : BaseNotification() {

    override fun getType(): NotificationType {
        return NotificationType.ONLINE
    }

    @Relationship("subject")
    var profile: Profile? = null
}