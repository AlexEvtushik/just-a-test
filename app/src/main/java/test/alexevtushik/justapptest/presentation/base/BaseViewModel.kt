package test.alexevtushik.justapptest.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

abstract class BaseViewModel<in View : BaseView<State>, State>(initialState: State) : ViewModel() {

    protected val state: BehaviorSubject<State> = BehaviorSubject.createDefault(initialState)
    protected val disposables = CompositeDisposable()

    abstract fun bindView(view: View)

    protected fun newState(reducer: State.() -> State) {
        state.value?.let { state.onNext(reducer(it)) }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}