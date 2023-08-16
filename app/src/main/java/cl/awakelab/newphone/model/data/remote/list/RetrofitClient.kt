package cl.awakelab.newphone.model.data.remote.list

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

class RetrofitClient {
    companion object {

        fun retrofitInstance(): PhoneApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PhoneApi::class.java)
        }
    }
}