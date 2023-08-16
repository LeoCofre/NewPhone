package cl.awakelab.newphone.model.data.local.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_phone")
data class PhoneEntity(
    @PrimaryKey val id: Int,
    val image: String,
    val name: String,
    val price: Int
)
