package info.anisuzzaman.mvvmarchitecture.repositories

import android.widget.Toast
import androidx.lifecycle.LiveData
import info.anisuzzaman.mvvmarchitecture.App
import info.anisuzzaman.mvvmarchitecture.repositories.api.UserWebservice
import info.anisuzzaman.mvvmarchitecture.repositories.database.dao.UserDao
import info.anisuzzaman.mvvmarchitecture.repositories.database.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by anisuzzaman on 24/9/20.
 */

@Singleton
class UserRepository @Inject constructor(private val webservice: UserWebservice, private val userDao: UserDao, private val executor: Executor) {
    fun getUser(userLogin: String): LiveData<User> {
        refreshUser(userLogin)
        return userDao.load(userLogin)
    }

    private fun refreshUser(userLogin: String) {
        executor.execute {
            val userExists = userDao.hasUser(userLogin, getMaxRefreshTime(Date())) != null
            if (!userExists) {
                webservice.getUser(userLogin)!!.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        Toast.makeText(App.context, "Data refreshed from network!", Toast.LENGTH_LONG).show()
                        executor.execute {
                            val user = response.body()
                            user!!.lastRefresh = Date()
                            userDao.save(user)
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(App.context, "Data refresh failed!", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }

    private fun getMaxRefreshTime(currentDate: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = currentDate
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES)
        return cal.time
    }

    companion object {
        private const val FRESH_TIMEOUT_IN_MINUTES = 1
    }
}