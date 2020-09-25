package info.anisuzzaman.mvvmarchitecture.repositories.database.converter

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by anisuzzaman on 24/9/20.
 */

object DateConverter {
    @JvmStatic
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @JvmStatic
    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}