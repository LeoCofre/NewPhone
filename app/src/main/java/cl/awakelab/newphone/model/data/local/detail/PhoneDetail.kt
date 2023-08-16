package cl.awakelab.newphone.model.data.local.detail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_phone_details")
data class PhoneDetailEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val price: Long,
    val image: String,
    val description: String,
    val lastPrice: Long,
    val credit: Boolean
)