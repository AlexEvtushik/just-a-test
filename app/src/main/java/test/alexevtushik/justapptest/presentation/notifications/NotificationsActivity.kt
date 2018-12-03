package test.alexevtushik.justapptest.presentation.notifications

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_notifications.*
import test.alexevtushik.justapptest.R
import test.alexevtushik.justapptest.R.id.view_notifications
import test.alexevtushik.justapptest.models.notifications.presentation.NotificationModel
import test.alexevtushik.justapptest.presentation.base.BaseActivity
import test.alexevtushik.justapptest.presentation.notifications.details.NotificationDetailsActivity

class NotificationsActivity : BaseActivity(), NotificationsView {

    private lateinit var viewModel: NotificationsViewModel
    private var notificationsAdapter: NotificationsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        initViews()
        viewModel = ViewModelProviders.of(this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return NotificationsViewModel(this@NotificationsActivity) as T
                }
            })[NotificationsViewModel::class.java]
        viewModel.bindView(this)
    }

    override fun render(state: NotificationsState) {
        with(state) {
            showProgress(progress)
            showError(error)
            showNotifications(notifications)
        }
    }

    private fun initViews() {
        notificationsAdapter = NotificationsAdapter(object : OnNotificationClickListener {
            override fun onClick(notification: NotificationModel) {
                startActivity(NotificationDetailsActivity.newIntent(this@NotificationsActivity, notification.id!!))
            }
        })
        view_notifications.adapter = notificationsAdapter
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

    private fun showNotifications(notifications: List<NotificationModel>) {
        notificationsAdapter?.items = notifications
        notificationsAdapter?.notifyDataSetChanged()
    }
}