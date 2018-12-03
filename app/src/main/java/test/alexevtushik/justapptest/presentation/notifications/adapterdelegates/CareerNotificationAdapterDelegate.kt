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

class CareerNotificationAdapterDelegate(
    private val notificationClickListener: OnNotificationClickListener
) : AbsListItemAdapterDelegate<NotificationModel, NotificationModel,
        CareerNotificationAdapterDelegate.CareerViewHolder>() {

    override fun isForViewType(item: NotificationModel, items: MutableList<NotificationModel>, position: Int): Boolean {
        return item.type == NotificationType.CAREER
    }

    override fun onCreateViewHolder(parent: ViewGroup): CareerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CareerViewHolder(inflater.inflate(R.layout.item_career_notification, parent, false))
    }

    override fun onBindViewHolder(item: NotificationModel, holder: CareerViewHolder, payloads: MutableList<Any>) {
        holder.itemView.setOnClickListener { notificationClickListener.onClick(item) }
        holder.notificationDate.text = DateTimeUtils.getFormattedDate(item.date!!)
        Picasso.with(holder.itemView.context)
            .load(R.drawable.ic_green_tick)
            .transform(ImageCircleTransformation())
            .into(holder.notificationIcon)
    }

    inner class CareerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val notificationIcon: ImageView = itemView.findViewById(R.id.view_notification_icon)
        val notificationDate: TextView = itemView.findViewById(R.id.view_notification_date)
    }
}