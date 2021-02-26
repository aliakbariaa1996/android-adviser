package ir.org.tavanesh.data.platform.engine

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.org.tavanesh.core.utils.DATABASE_NAME
import ir.org.tavanesh.core.utils.DATABASE_VERSION
import ir.org.tavanesh.data.models.CityEntity
import ir.org.tavanesh.data.models.LogyEntity
import ir.org.tavanesh.data.platform.datasource.CityDao
import ir.org.tavanesh.data.platform.datasource.LogyDao


@Database(entities = [CityEntity::class, LogyEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun logyDao(): LogyDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

