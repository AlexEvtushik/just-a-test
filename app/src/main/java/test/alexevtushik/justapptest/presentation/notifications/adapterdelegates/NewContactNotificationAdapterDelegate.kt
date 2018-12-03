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
import test.alexevtushik.justapptest.presentation.utils.StringFormatter

class NewContactNotificationAdapterDelegate(
    private val notificationClickListener: OnNotificationClickListener
) : AbsListItemAdapterDelegate<NotificationModel, NotificationModel,
            NewContactNotificationAdapterDelegate.NewContactViewHolder>() {

    override fun isForViewType(item: NotificationModel, items: MutableList<NotificationModel>, position: Int): Boolean {
        return item.type == NotificationType.NEW_CONTACT
    }

    override fun onCreateViewHolder(parent: ViewGroup): NewContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewContactViewHolder(inflater.inflate(R.layout.item_new_contact_notification, parent, false))
    }

    override fun onBindViewHolder(item: NotificationModel, holder: NewContactViewHolder, payloads: MutableList<Any>) {
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

    inner class NewContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val notificationIcon: ImageView = itemView.findViewById(R.id.view_notification_icon)
        val notificationTitle: TextView = itemView.findViewById(R.id.view_notification_title)
        val notificationDate: TextView = itemView.findViewById(R.id.view_notification_date)
        val notificationTitleTemplate: String =
            itemView.resources.getString(R.string.notifications_message_wants_add_you_to_contacts)
        val notificationNegativeAction: Button = itemView.findViewById(R.id.view_notification_negative_action)

        init {
            notificationNegativeAction.paintFlags = notificationNegativeAction.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }
}