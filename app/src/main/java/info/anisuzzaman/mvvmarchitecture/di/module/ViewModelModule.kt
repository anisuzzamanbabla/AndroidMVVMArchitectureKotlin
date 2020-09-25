package info.anisuzzaman.mvvmarchitecture.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.anisuzzaman.mvvmarchitecture.repositories.UserRepository
import info.anisuzzaman.mvvmarchitecture.view_models.FactoryViewModel
import info.anisuzzaman.mvvmarchitecture.view_models.UserProfileViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by anisuzzaman on 24/9/20.
 */

@Module
class ViewModelModule {

    @Provides
    fun bindUserProfileViewModel(userRepository: UserRepository): ViewModel {
        return UserProfileViewModel(userRepository)
    }

    @Provides
    fun bindViewModelFactory(factory: FactoryViewModel): ViewModelProvider.Factory {
        return factory
    }
}