package info.anisuzzaman.mvvmarchitecture.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.anisuzzaman.mvvmarchitecture.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by anisuzzaman on 24/9/20.
 */

@Singleton
class FactoryViewModel @Inject constructor(private val repository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            UserProfileViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}