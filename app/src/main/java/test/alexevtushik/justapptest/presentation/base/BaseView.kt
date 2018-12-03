package test.alexevtushik.justapptest.presentation.base

interface BaseView<in State> {

    fun render(state: State)
}