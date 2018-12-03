package test.alexevtushik.justapptest.presentation.notifications.adapterdelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.squareup.picasso.Picasso
import test.alexevtushik.justapptest.R
import test.alexevtushik.justapptest.models.notifications.network.NotificationType
import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel
import test.alexevtushik.justapptest.presentation.notifications.OnNotificationClickListener
import test.alexevtushik.justapptest.presentation.utils.DateTimeUtils
import test.alexevtushik.justapptest.presentation.utils.ImageCircleTransformation
import test.alexevtushik.justapptest.presentation.utils.StringFormatter

class OnlineNotificationAdapterDelegate(
    private val notificationClickListener: OnNotificationClickListener
) : AbsListItemAdapterDelegate<NotificationModel, NotificationModel,
        OnlineNotificationAdapterDelegate.AbsentPersonViewHolder>() {

    override fun isForViewType(item: NotificationModel, items: MutableList<NotificationModel>, position: Int): Boolean {
        return item.type == NotificationType.ONLINE
    }

    override fun onCreateViewHolder(parent: ViewGroup): AbsentPersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AbsentPersonViewHolder(inflater.inflate(R.layout.item_online_notification, parent, false))
    }

    override fun onBindViewHolder(item: NotificationModel, holder: AbsentPersonViewHolder, payloads: MutableList<Any>) {
        holder.itemView.setOnClickListener { notificationClickListener.onClick(item) }
        holder.notificationTitle.text =
                StringFormatter.getFormattedBoldName(
                    holder.notificationTitleTemplate,
                    item.profile?.firstName!!,
                    item.profile?.lastName!!
                )
        holder.notificationDate.text = DateTimeUtils.getFormattedDate(item.date!!)
        Picasso.with(holder.itemView.context)
            .load(item.profile?.avatarUrl)
            .transform(ImageCircleTransformation())
            .into(holder.notificationIcon)
    }

    inner class AbsentPersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val notificationIcon: ImageView = itemView.findViewById(R.id.view_notification_icon)
        val notificationTitle: TextView = itemView.findViewById(R.id.view_notification_title)
        val notificationDate: TextView = itemView.findViewById(R.id.view_notification_date)
        val notificationTitleTemplate: String =
            itemView.resources.getString(R.string.notifications_message_profile_available_online)
    }
}