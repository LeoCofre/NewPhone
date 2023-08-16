package cl.awakelab.newphone.model

import android.util.Log
import androidx.lifecycle.LiveData
import cl.awakelab.newphone.model.data.local.detail.PhoneDetailEntity
import cl.awakelab.newphone.model.data.local.list.PhoneDao
import cl.awakelab.newphone.model.data.local.list.PhoneEntity
import cl.awakelab.newphone.model.data.remote.detail.PhoneDetail
import cl.awakelab.newphone.model.data.remote.list.PhoneApi

class Repository(private val phoneApi: PhoneApi, private val phoneDao: PhoneDao) {

    fun getPhoneEntity(): LiveData<List<PhoneEntity>> = phoneDao.getPhones()

    fun getPhoneDetailEntity(id: Long): LiveData<PhoneDetailEntity> = phoneDao.getPhoneDetails(id)

    suspend fun getPhones() {
        try {
            val response = phoneApi.getPhones()
            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let {
                    val phoneEntity = it.map { it.transformToEntity() }
                    //phoneDao.insertPhone(phoneEntity)
                }
            }
        } catch (exception: Exception) {
            Log.e("cath", "")
        }
    }
}

fun PhoneEntity.transformToEntity(): PhoneEntity =
    PhoneEntity(this.id, this.name, this.price, this.image)

fun PhoneDetail.transformToDetailEntity(id: Long): PhoneDetailEntity =
    PhoneDetailEntity(
        this.id,
        this.name,
        this.price,
        this.image,
        this.description,
        this.lastPrice,
        this.credit
    )
