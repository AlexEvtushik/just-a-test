package test.alexevtushik.justapptest.models.notifications.network

import com.github.jasminb.jsonapi.annotations.Relationship
import com.github.jasminb.jsonapi.annotations.Type

@Type("add-to-favorites-notifications")
class FavoriteNotification : BaseNotification() {

    override fun getType(): NotificationType {
        return NotificationType.FAVORITE
    }

    @Relationship("subject")
    var profile: Profile? = null
}