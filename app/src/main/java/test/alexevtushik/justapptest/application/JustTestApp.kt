package test.alexevtushik.justapptest.application

import android.app.Application
import android.content.Context
import space.traversal.kapsule.transitive
import test.alexevtushik.justapptest.di.*

class JustTestApp : Application() {

    companion object {
        fun module(context: Context) = (context.applicationContext as JustTestApp).module
    }

    private val module = createModule()

    protected fun createModule() = Module(
        rxModule = RxModuleImpl(),
        networkModule = NetworkModuleImpl(),
        mappersModule = MappersModuleImpl(),
        repositoriesModule = RepositoriesModuleImpl(),
        interactorsModule = InteractorsModuleImpl()
    ).transitive()
}