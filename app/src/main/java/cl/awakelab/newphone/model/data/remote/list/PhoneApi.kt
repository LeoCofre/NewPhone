package cl.awakelab.newphone.model.data.remote.list

import retrofit2.Response
import retrofit2.http.GET

interface PhoneApi {
    @GET("products/")
    fun getPhones(): Response<List<Phone>>
}