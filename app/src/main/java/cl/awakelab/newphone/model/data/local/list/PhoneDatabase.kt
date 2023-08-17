package cl.awakelab.newphone.model.data.local.list

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.awakelab.newphone.model.data.local.detail.PhoneDetailEntity

@Database(entities = [PhoneEntity::class, PhoneDetailEntity::class], version = 1)
abstract class PhoneDatabase: RoomDatabase() {

    abstract fun getPhoneDao(): PhoneDao

    companion object{
        @Volatile
        private var INSTANCE: PhoneDatabase? = null

        fun getDataBase(context: Context): PhoneDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDatabase::class.java,
                    "phones_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}