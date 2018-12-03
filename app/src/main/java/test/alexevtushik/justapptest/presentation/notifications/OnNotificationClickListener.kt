package test.alexevtushik.justapptest.presentation.notifications

import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel

interface OnNotificationClickListener {

    fun onClick(notification: NotificationModel)
}