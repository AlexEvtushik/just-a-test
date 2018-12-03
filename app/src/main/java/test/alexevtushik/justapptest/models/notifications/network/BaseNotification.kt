package test.alexevtushik.justapptest.models.notifications.network

import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type

@Type("notification")
abstract class BaseNotification {

    @Id
    var id: String? = null
    var date: String? = null

    abstract fun getType(): NotificationType
}