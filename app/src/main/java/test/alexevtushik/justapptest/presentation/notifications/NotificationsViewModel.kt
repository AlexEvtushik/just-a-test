package test.alexevtushik.justapptest.presentation.notifications

import android.annotation.SuppressLint
import android.content.Context
import space.traversal.kapsule.Injects
import space.traversal.kapsule.inject
import space.traversal.kapsule.required
import test.alexevtushik.justapptest.application.JustTestApp
import test.alexevtushik.justapptest.di.Module
import test.alexevtushik.justapptest.presentation.base.BaseViewModel
import timber.log.Timber

class NotificationsViewModel(context: Context) :
    BaseViewModel<NotificationsView, NotificationsState>(NotificationsState()), Injects<Module> {

    private val interactor by required { notificationsInteractor }
    private val scheduler by required { mainScheduler }

    init {
        inject(JustTestApp.module(context))
    }

    @SuppressLint("CheckResult")
    override fun bindView(view: NotificationsView) {
        state.observeOn(scheduler)
            .subscribe({ state -> view.render(state) },
                { throwable -> Timber.e(throwable) },
                { /*do nothing*/ },
                { disposable -> disposables.add(disposable) })

        interactor.getNotifications()
            .toObservable()
            .observeOn(scheduler)
            .subscribe({ notificationModels ->
                newState {
                    copy(progress = false, notifications = notificationModels)
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