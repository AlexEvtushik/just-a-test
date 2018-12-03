package test.alexevtushik.justapptest.presentation.notifications.details

import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel

data class NotificationDetailsState(
    val progress: Boolean = false,
    val error: Boolean = false,
    val notification: NotificationModel = NotificationModel()
)