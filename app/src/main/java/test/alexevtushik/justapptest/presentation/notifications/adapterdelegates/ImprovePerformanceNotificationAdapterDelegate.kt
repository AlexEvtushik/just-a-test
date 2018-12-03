package test.alexevtushik.justapptest.presentation.notifications.adapterdelegates

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

class ImprovePerformanceNotificationAdapterDelegate(
    private val notificationClickListener: OnNotificationClickListener
) : AbsListItemAdapterDelegate<NotificationModel, NotificationModel,
        ImprovePerformanceNotificationAdapterDelegate.PerformanceViewHolder>() {

    override fun isForViewType(item: NotificationModel, items: MutableList<NotificationModel>, position: Int): Boolean {
        return item.type == NotificationType.IMPROVE_PERFORMANCE
    }

    override fun onCreateViewHolder(parent: ViewGroup): PerformanceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PerformanceViewHolder(inflater.inflate(R.layout.item_improve_performance_notification, parent, false))
    }

    override fun onBindViewHolder(item: NotificationModel, holder: PerformanceViewHolder, payloads: MutableList<Any>) {
        holder.itemView.setOnClickListener { notificationClickListener.onClick(item) }
        holder.notificationSubtitle.text = String.format(holder.notificationSubtitleTemplate, item.peopleViewedCount)
        holder.notificationDate.text = DateTimeUtils.getFormattedDate(item.date!!)
        Picasso.with(holder.itemView.context)
            .load(R.drawable.ic_green_arrow_up)
            .transform(ImageCircleTransformation())
            .into(holder.notificationIcon)
    }

    inner class PerformanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val notificationIcon: ImageView = itemView.findViewById(R.id.view_notification_icon)
        val notificationSubtitle: TextView = itemView.findViewById(R.id.view_notification_subtitle)
        val notificationDate: TextView = itemView.findViewById(R.id.view_notification_date)
        val notificationSubtitleTemplate: String =
            itemView.resources.getString(R.string.notifications_message_improve_performance)
        val notificationNegativeAction: Button = itemView.findViewById(R.id.view_notification_negative_action)

        init {
            notificationNegativeAction.paintFlags = notificationNegativeAction.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }
}