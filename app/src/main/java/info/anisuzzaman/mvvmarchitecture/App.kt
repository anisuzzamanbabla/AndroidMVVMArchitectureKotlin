package info.anisuzzaman.mvvmarchitecture

import android.app.Activity
import android.app.Application
import android.content.Context
import info.anisuzzaman.mvvmarchitecture.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by anisuzzaman on 24/9/20.
 */

class App : Application(), HasActivityInjector {

    @JvmField
    @Inject
    var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null
    override fun onCreate() {
        super.onCreate()
        initDagger()
        context = applicationContext
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector!!
    }

    private fun initDagger() {
        DaggerAppComponent.builder().application(this)!!.build()!!.inject(this)
    }

    companion object {
        @JvmField
        var context: Context? = null
    }
}