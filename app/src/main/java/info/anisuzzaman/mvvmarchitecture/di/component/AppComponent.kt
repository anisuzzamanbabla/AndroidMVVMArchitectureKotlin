package info.anisuzzaman.mvvmarchitecture.di.component

import android.app.Application
import info.anisuzzaman.mvvmarchitecture.App
import info.anisuzzaman.mvvmarchitecture.di.module.ActivityModule
import info.anisuzzaman.mvvmarchitecture.di.module.AppModule
import info.anisuzzaman.mvvmarchitecture.di.module.FragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by anisuzzaman on 24/9/20.
 */

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityModule::class, FragmentModule::class, AppModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}