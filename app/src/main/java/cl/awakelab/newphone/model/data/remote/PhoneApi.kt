package cl.awakelab.newphone.model.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface PhoneApi {
    @GET("products/")
    fun getPhones(): Response<List<Phone>>
}