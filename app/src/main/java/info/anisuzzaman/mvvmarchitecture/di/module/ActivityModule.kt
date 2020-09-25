package info.anisuzzaman.mvvmarchitecture.di.module

import info.anisuzzaman.mvvmarchitecture.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by anisuzzaman on 24/9/20.
 */

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}