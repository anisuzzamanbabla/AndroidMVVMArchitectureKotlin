package info.anisuzzaman.mvvmarchitecture.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import info.anisuzzaman.mvvmarchitecture.repositories.UserRepository
import info.anisuzzaman.mvvmarchitecture.repositories.database.entity.User
import javax.inject.Inject

/**
 * Created by anisuzzaman on 24/9/20.
 */

class UserProfileViewModel @Inject constructor(private val userRepo: UserRepository) : ViewModel() {
    var user: LiveData<User>? = null
        private set

    fun init(userId: String?) {
        if (user != null) {
            return
        }
        user = userRepo.getUser(userId!!)
    }
}