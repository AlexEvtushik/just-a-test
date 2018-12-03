package test.alexevtushik.justapptest.presentation.notifications

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel
import test.alexevtushik.justapptest.presentation.notifications.adapterdelegates.*

class NotificationsAdapter(val notificationClickListener: OnNotificationClickListener) : ListDelegationAdapter<List<NotificationModel>>() {

    init {
        delegatesManager.addDelegate(FavoriteNotificationAdapterDelegate(notificationClickListener))
        delegatesManager.addDelegate(OnlineNotificationAdapterDelegate(notificationClickListener))
        delegatesManager.addDelegate(UpdateCharacteristicsNotificationAdapterDelegate(notificationClickListener))
        delegatesManager.addDelegate(CareerNotificationAdapterDelegate(notificationClickListener))
        delegatesManager.addDelegate(NewContactNotificationAdapterDelegate(notificationClickListener))
        delegatesManager.addDelegate(ImprovePerformanceNotificationAdapterDelegate(notificationClickListener))
    }
}