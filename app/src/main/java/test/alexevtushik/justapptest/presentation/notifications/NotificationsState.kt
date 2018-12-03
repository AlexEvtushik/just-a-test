package test.alexevtushik.justapptest.presentation.notifications

import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel

data class NotificationsState(
    val progress: Boolean = false,
    val error: Boolean = false,
    val notifications: List<NotificationModel> = listOf()
)