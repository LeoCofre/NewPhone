package cl.awakelab.newphone.model.data.local.list

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.awakelab.newphone.model.data.local.detail.PhoneDetailEntity

@Dao
interface PhoneDao {

    //Insrtamos datos a la lista
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phoneEntity: List<PhoneEntity>)

    @Query("Select * from tabla_phone order by id asc")
    fun getPhones():LiveData<List<PhoneEntity>>

    //Insertamos datos al detalle
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneDetail(phoneDetailEntity: PhoneDetailEntity)

    @Query("Select * from table_phone_details where id = :id")
    fun getPhoneDetails(id: Long): LiveData<PhoneDetailEntity>
}