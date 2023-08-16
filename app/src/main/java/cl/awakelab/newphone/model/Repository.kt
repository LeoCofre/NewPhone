package cl.awakelab.newphone.model

import androidx.lifecycle.LiveData
import cl.awakelab.newphone.model.data.local.list.PhoneDao
import cl.awakelab.newphone.model.data.local.list.PhoneEntity
import cl.awakelab.newphone.model.data.remote.PhoneApi

class Repository(private val phoneApi: PhoneApi, private val phoneDao: PhoneDao) {

    fun getPhoneEntity(): LiveData<List<PhoneEntity>> = phoneDao.getPhones()



}