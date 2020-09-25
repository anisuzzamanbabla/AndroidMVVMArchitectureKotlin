package info.anisuzzaman.mvvmarchitecture.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import info.anisuzzaman.mvvmarchitecture.repositories.database.entity.User
import info.anisuzzaman.mvvmarchitecture.utils.LiveDataTestUtil
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Created by anisuzzaman on 24/9/20.
 */

@RunWith(AndroidJUnit4::class)
class UserDaoTest : DbTest() {
    @Test
    @Throws(InterruptedException::class)
    fun insertAndLoad() {
        val user = User("4880496", "anisuzzamanbabla", "https://avatars2.githubusercontent.com/u/4880496?v=4", "Anisuzzaman", "D Money Bangladesh Limited", "http://anisuzzaman.info", Date())
        db!!.userDao()!!.save(user)
        val loaded = LiveDataTestUtil().getValue(db!!.userDao()!!.load(user.login!!))
        MatcherAssert.assertThat(loaded!!.login, CoreMatchers.`is`("anisuzzamanbabla"))
    }
}