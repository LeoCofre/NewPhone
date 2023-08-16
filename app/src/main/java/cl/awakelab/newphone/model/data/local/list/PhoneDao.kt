package cl.awakelab.newphone.model.data.local.list

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phoneEntity: PhoneEntity)

    @Query("Select * from tabla_phone order by id asc")
    fun getPhones():LiveData<List<PhoneEntity>>
}