package cl.awakelab.newphone.model.data.local.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_phone")
data class PhoneEntity(
    @PrimaryKey val id: Long,
    val image: Long,
    val name: Long,
    val price: Long
)
