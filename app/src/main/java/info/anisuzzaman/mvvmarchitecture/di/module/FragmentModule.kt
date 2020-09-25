package info.anisuzzaman.mvvmarchitecture.di.module

import info.anisuzzaman.mvvmarchitecture.fragments.UserProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by anisuzzaman on 24/9/20.
 */

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeUserProfileFragment(): UserProfileFragment
}