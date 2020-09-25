package info.anisuzzaman.mvvmarchitecture.repositories.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import info.anisuzzaman.mvvmarchitecture.repositories.database.converter.DateConverter
import info.anisuzzaman.mvvmarchitecture.repositories.database.dao.UserDao
import info.anisuzzaman.mvvmarchitecture.repositories.database.entity.User

/**
 * Created by anisuzzaman on 24/9/20.
 */

@Database(entities = [User::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?

    companion object {
        private val INSTANCE: MyDatabase? = null
    }
}