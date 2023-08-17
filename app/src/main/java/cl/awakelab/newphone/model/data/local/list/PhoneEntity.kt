package cl.awakelab.newphone.model.data.local.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_phone")
data class PhoneEntity(
    @PrimaryKey val id: Long,
    val image: String,
    val name: String,
    val price: Long
)
