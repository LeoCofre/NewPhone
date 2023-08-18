package cl.awakelab.newphone.model

import android.util.Log
import androidx.lifecycle.LiveData
import cl.awakelab.newphone.model.data.local.detail.PhoneDetailEntity
import cl.awakelab.newphone.model.data.local.list.PhoneDao
import cl.awakelab.newphone.model.data.local.list.PhoneEntity
import cl.awakelab.newphone.model.data.remote.list.PhoneApi
import cl.awakelab.newphone.model.data.remote.transformToDetailEntity
import cl.awakelab.newphone.model.data.remote.transformToEntity

class Repository(private val phoneApi: PhoneApi, private val phoneDao: PhoneDao) {

    fun getPhoneEntity(): LiveData<List<PhoneEntity>> = phoneDao.getPhones()

    fun getPhoneDetailEntity(id: Long): LiveData<PhoneDetailEntity> = phoneDao.getPhoneDetails(id)

    suspend fun getPhones() {

        try {
            val response = phoneApi.getDataPhones()
            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let {
                    val phoneEntity = it.map { it.transformToEntity() }
                    phoneDao.insertPhone(phoneEntity)
                }
            }
        } catch (excepcion: Exception) {
            Log.e("Repository", "En el repositorio")
        }
    }

    suspend fun getPhoneDetails(id: Long) {
        try {
            val response = phoneApi.getDataPhoneDetails(id)
            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let {
                    val phoneDetailEntity = it.transformToDetailEntity()
                    phoneDao.insertPhoneDetail(phoneDetailEntity)
                }
            }
        }catch (exception: Exception){
            Log.e("RepoDetail", " En el repoDetail")
        }

    }
}


