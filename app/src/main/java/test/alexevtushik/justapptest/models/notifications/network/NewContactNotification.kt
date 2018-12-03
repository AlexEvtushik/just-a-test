package test.alexevtushik.justapptest.models.notifications.network

import com.github.jasminb.jsonapi.annotations.Relationship
import com.github.jasminb.jsonapi.annotations.Type

@Type("add-to-contacts-notifications")
class NewContactNotification : BaseNotification() {

    override fun getType(): NotificationType {
        return NotificationType.NEW_CONTACT
    }

    @Relationship("subject")
    var profile: Profile? = null
}