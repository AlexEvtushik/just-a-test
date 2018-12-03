package test.alexevtushik.justapptest.presentation.notifications.details

import android.annotation.SuppressLint
import android.content.Context
import space.traversal.kapsule.Injects
import space.traversal.kapsule.inject
import space.traversal.kapsule.required
import test.alexevtushik.justapptest.application.JustTestApp
import test.alexevtushik.justapptest.di.Module
import test.alexevtushik.justapptest.presentation.base.BaseViewModel
import timber.log.Timber

class NotificationDetailsViewModel(context: Context) :
    BaseViewModel<NotificationDetailsView, NotificationDetailsState>(NotificationDetailsState()), Injects<Module> {

    private val interactor by required { notificationsInteractor }
    private val scheduler by required { mainScheduler }

    init {
        inject(JustTestApp.module(context))
    }

    @SuppressLint("CheckResult")
    override fun bindView(view: NotificationDetailsView) {
        state.observeOn(scheduler)
            .subscribe({ state -> view.render(state) },
                { throwable -> Timber.e(throwable) },
                { /*do nothing*/ },
                { disposable -> disposables.add(disposable) })

        view.loadNotificationIntent
            .flatMap { notificationId: String -> interactor.getNotification(notificationId).toObservable() }
            .observeOn(scheduler)
            .subscribe({ notificationModel ->
                newState {
                    copy(progress = false, notification = notificationModel)
                }
            }, { throwable ->
                newState {
                    copy(progress = false, error = true)
                }
            }, {
                newState {
                    copy(progress = false)
                }
            }, { disposable ->
                newState {
                    copy(progress = true)
                }
                disposables.add(disposable)
            })
    }

}