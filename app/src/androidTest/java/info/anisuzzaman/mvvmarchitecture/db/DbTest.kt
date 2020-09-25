package info.anisuzzaman.mvvmarchitecture.db

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import info.anisuzzaman.mvvmarchitecture.repositories.database.MyDatabase
import org.junit.After
import org.junit.Before

/**
 * Created by anisuzzaman on 24/9/20.
 */

abstract class DbTest {

    protected var db: MyDatabase? = null
    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
                MyDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        db!!.close()
    }
}