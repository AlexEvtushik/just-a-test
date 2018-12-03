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

class FavoriteNotificationAdapterDelegate(
    private val notificationClickListener: OnNotificationClickListener
) : AbsListItemAdapterDelegate<NotificationModel, NotificationModel,
        FavoriteNotificationAdapterDelegate.FavoriteViewHolder>() {

    override fun isForViewType(item: NotificationModel, items: MutableList<NotificationModel>, position: Int): Boolean {
        return item.type == NotificationType.FAVORITE
    }

    override fun onCreateViewHolder(parent: ViewGroup): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoriteViewHolder(inflater.inflate(R.layout.item_favorite_notification, parent, false))
    }

    override fun onBindViewHolder(item: NotificationModel, holder: FavoriteViewHolder, payloads: MutableList<Any>) {
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

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val notificationIcon: ImageView = itemView.findViewById(R.id.view_notification_icon)
        val notificationTitle: TextView = itemView.findViewById(R.id.view_notification_title)
        val notificationDate: TextView = itemView.findViewById(R.id.view_notification_date)
        val notificationTitleTemplate: String =
            itemView.resources.getString(R.string.notifications_message_added_you_to_favorite)
    }
}