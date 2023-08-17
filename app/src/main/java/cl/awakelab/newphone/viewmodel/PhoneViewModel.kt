package cl.awakelab.newphone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.awakelab.newphone.model.Repository
import cl.awakelab.newphone.model.data.local.list.PhoneDao
import cl.awakelab.newphone.model.data.local.list.PhoneDatabase
import cl.awakelab.newphone.model.data.remote.list.RetrofitClient
import kotlinx.coroutines.launch

class PhoneViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = TODO()
    fun phonesLiveData() = repository.getPhoneEntity()
    fun phoneDetailLiveData(id: Long) = repository.getPhoneDetailEntity(id)

    init {
        val api = RetrofitClient.retrofitInstance()
        val phoneDatabase: PhoneDao = PhoneDatabase.getDataBase(application).getPhoneDao()
        repository = Repository(api, phoneDatabase)
    }

    fun getPhoneViewModel() = viewModelScope.launch { repository.getPhones() }
    fun getPhoneDetailsViewModel(id: Long) = viewModelScope.launch {
        repository.getPhoneDetails(id)
    }

}