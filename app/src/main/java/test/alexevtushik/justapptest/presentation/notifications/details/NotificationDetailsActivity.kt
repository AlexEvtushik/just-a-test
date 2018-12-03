package test.alexevtushik.justapptest.presentation.notifications.details

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_notification_details.*
import test.alexevtushik.justapptest.R
import test.alexevtushik.justapptest.models.notifications.network.NotificationType
import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel
import test.alexevtushik.justapptest.presentation.base.BaseActivity
import test.alexevtushik.justapptest.presentation.utils.ImageCircleTransformation
import test.alexevtushik.justapptest.presentation.utils.StringFormatter

class NotificationDetailsActivity : BaseActivity(), NotificationDetailsView {

    companion object {
        private const val NOTIFICATION_ID: String = "notification_id"

        fun newIntent(context: Context, notificationId: String): Intent {
            val intent = Intent(context, NotificationDetailsActivity::class.java)
            intent.putExtra(NOTIFICATION_ID, notificationId)
            return intent
        }
    }

    private lateinit var viewModel : NotificationDetailsViewModel
    private var notificationId: String? = null

    override val loadNotificationIntent: Observable<String>
        get() = Observable.just(notificationId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        notificationId = intent.getStringExtra(NOTIFICATION_ID)
        viewModel = ViewModelProviders.of(this,
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                   return NotificationDetailsViewModel(this@NotificationDetailsActivity) as T
                }
            })[NotificationDetailsViewModel::class.java]
        viewModel.bindView(this)
    }

    override fun render(state: NotificationDetailsState) {
        with(state) {
            showProgress(progress)
            showError(error)
            showNotification(notification)
        }
    }

    private fun showProgress(show: Boolean) {
        if (show) {
            view_progress.show()
        } else {
            view_progress.hide()
        }
    }

    private fun showError(show: Boolean) {
        if (show) {
            Snackbar
                .make(view_root, R.string.notifications_error_something_went_wrong, Snackbar.LENGTH_LONG)
                .show()
        }
    }

    private fun showNotification(notification: NotificationModel) {
        when (notification.type) {
            NotificationType.FAVORITE -> showFavoriteNotification(notification)
            NotificationType.ONLINE -> showOnlineNotification(notification)
            NotificationType.UPDATE_CHARACTERISTICS -> showUpdateCharacteristicsNotification()
            NotificationType.NEW_CONTACT -> showNewContactNotification(notification)
            NotificationType.CAREER -> showCareerNotification()
            NotificationType.IMPROVE_PERFORMANCE -> showPerformanceNotification(notification)
        }
    }

    private fun showFavoriteNotification(notification: NotificationModel) {
        title = getString(R.string.notifications_label_favorite_notification)
        view_notification_subtitle.text = StringFormatter.getFormattedBoldName(
            getString(R.string.notifications_message_added_you_to_favorite),
            notification.profile?.firstName!!, notification.profile?.lastName!!
        )
        view_separator.visibility = View.VISIBLE
        view_actions.visibility = View.GONE
        Picasso.with(this)
            .load(notification.profile?.avatarUrl)
            .transform(ImageCircleTransformation())
            .into(view_notification_icon)
    }

    private fun showOnlineNotification(notification: NotificationModel) {
        title = getString(R.string.notifications_label_online_notification)
        view_notification_subtitle.text = StringFormatter.getFormattedBoldName(
            getString(R.string.notifications_message_profile_available_online),
            notification.profile?.firstName!!, notification.profile?.lastName!!
        )
        view_separator.visibility = View.VISIBLE
        view_actions.visibility = View.GONE
        Picasso.with(this)
            .load(notification.profile?.avatarUrl)
            .transform(ImageCircleTransformation())
            .into(view_notification_icon)
    }

    private fun showUpdateCharacteristicsNotification() {
        title = getString(R.string.notifications_label_characteristics_notification)
        view_notification_subtitle.text = getString(R.string.notifications_message_characteristics_updated)
        view_separator.visibility = View.VISIBLE
        view_actions.visibility = View.VISIBLE
        view_notification_positive_action.text = getString(R.string.notifications_label_update_now)
        view_notification_negative_action.text = getString(R.string.notifications_label_update_later)
        view_notification_negative_action.paintFlags = view_notification_negative_action.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        Picasso.with(this)
            .load(R.drawable.ic_orange_exclamation)
            .transform(ImageCircleTransformation())
            .into(view_notification_icon)
    }

    private fun showCareerNotification() {
        title = getString(R.string.notifications_label_career_notification)
        view_notification_subtitle.text = getString(R.string.notifications_message_career_confirmed)
        view_separator.visibility = View.VISIBLE
        view_actions.visibility = View.GONE
        Picasso.with(this)
            .load(R.drawable.ic_green_tick)
            .transform(ImageCircleTransformation())
            .into(view_notification_icon)
    }

    private fun showNewContactNotification(notification: NotificationModel) {
        title = getString(R.string.notifications_label_contacts_notification)
        view_notification_subtitle.text = StringFormatter.getFormattedBoldName(
            getString(R.string.notifications_message_wants_add_you_to_contacts),
            notification.profile?.firstName!!, notification.profile?.lastName!!
        )
        view_separator.visibility = View.VISIBLE
        view_actions.visibility = View.VISIBLE
        view_notification_positive_action.text = getString(R.string.notifications_label_accept)
        view_notification_negative_action.text = getString(R.string.notifications_label_decline)
        view_notification_negative_action.paintFlags = view_notification_negative_action.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        Picasso.with(this)
            .load(notification.profile?.avatarUrl)
            .transform(ImageCircleTransformation())
            .into(view_notification_icon)
    }

    private fun showPerformanceNotification(notification: NotificationModel) {
        title = getString(R.string.notifications_label_performance_notification)
        view_notification_subtitle.text = getString(R.string.notifications_message_improve_performance, notification.peopleViewedCount)
        view_separator.visibility = View.VISIBLE
        view_actions.visibility = View.VISIBLE
        view_notification_positive_action.text = getString(R.string.notifications_label_my_profile)
        view_notification_negative_action.text = getString(R.string.notifications_label_update_later)
        view_notification_negative_action.paintFlags = view_notification_negative_action.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        Picasso.with(this)
            .load(R.drawable.ic_green_arrow_up)
            .transform(ImageCircleTransformation())
            .into(view_notification_icon)
    }
}