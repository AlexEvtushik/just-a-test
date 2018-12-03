package test.alexevtushik.justapptest.presentation.notifications.details

import io.reactivex.Observable
import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel
import test.alexevtushik.justapptest.presentation.base.BaseView

interface NotificationDetailsView : BaseView<NotificationDetailsState> {

    val loadNotificationIntent: Observable<String>
}