package cl.awakelab.newphone.model.data.remote.list

import cl.awakelab.newphone.model.data.remote.detail.PhoneDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneApi {
    @GET("products/")
    suspend fun getDataPhones(): Response<List<Phone>>

    @GET("details/{id}")
    suspend fun getDataPhoneDetails(@Path("id") id: Long): Response<PhoneDetail>
}