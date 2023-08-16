package cl.awakelab.newphone.model.data.remote.detail

data class PhoneDetail (
    val id: Long,
    val name: String,
    val price: Long,
    val image: String,
    val description: String,
    val lastPrice: Long,
    val credit: Boolean
)