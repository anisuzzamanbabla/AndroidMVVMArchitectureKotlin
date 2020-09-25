package info.anisuzzaman.mvvmarchitecture.repositories.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import info.anisuzzaman.mvvmarchitecture.repositories.database.entity.User
import java.util.*

/**
 * Created by anisuzzaman on 24/9/20.
 */

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User)

    @Query("SELECT * FROM user WHERE login = :userLogin")
    fun load(userLogin: String): LiveData<User>

    @Query("SELECT * FROM user WHERE login = :userLogin AND lastRefresh > :lastRefreshMax LIMIT 1")
    fun hasUser(userLogin: String, lastRefreshMax: Date): User?
}