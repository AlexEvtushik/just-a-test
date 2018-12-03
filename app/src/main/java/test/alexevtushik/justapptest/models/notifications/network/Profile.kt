package test.alexevtushik.justapptest.models.notifications.network

import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type

@Type("people")
class Profile {

    @Id
    var id: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var avatarUrl: String? = null
}