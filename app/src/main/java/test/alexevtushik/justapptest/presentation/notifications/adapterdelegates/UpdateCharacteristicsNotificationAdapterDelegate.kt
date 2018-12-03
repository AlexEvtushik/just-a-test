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

class UpdateCharacteristicsNotificationAdapterDelegate(
    private val notificationClickListener: OnNotificationClickListener
) : AbsListItemAdapterDelegate<NotificationModel, NotificationModel,
        UpdateCharacteristicsNotificationAdapterDelegate.CharacteristicsViewHolder>() {

    override fun isForViewType(item: NotificationModel, items: MutableList<NotificationModel>, position: Int): Boolean {
        return item.type == NotificationType.UPDATE_CHARACTERISTICS
    }

    override fun onCreateViewHolder(parent: ViewGroup): CharacteristicsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharacteristicsViewHolder(inflater.inflate(R.layout.item_update_characteristics_notification, parent, false))
    }

    override fun onBindViewHolder(item: NotificationModel, holder: CharacteristicsViewHolder, payloads: MutableList<Any>) {
        holder.itemView.setOnClickListener { notificationClickListener.onClick(item) }
        holder.notificationDate.text = DateTimeUtils.getFormattedDate(item.date!!)
        Picasso.with(holder.itemView.context)
            .load(R.drawable.ic_orange_exclamation)
            .transform(ImageCircleTransformation())
            .into(holder.notificationIcon)
    }

    inner class CharacteristicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val notificationIcon: ImageView = itemView.findViewById(R.id.view_notification_icon)
        val notificationDate: TextView = itemView.findViewById(R.id.view_notification_date)
        val notificationNegativeAction: Button = itemView.findViewById(R.id.view_notification_negative_action)

        init {
            notificationNegativeAction.paintFlags = notificationNegativeAction.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }
}