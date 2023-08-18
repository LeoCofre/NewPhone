package cl.awakelab.newphone.model.data.remote

import cl.awakelab.newphone.model.data.local.detail.PhoneDetailEntity
import cl.awakelab.newphone.model.data.local.list.PhoneEntity
import cl.awakelab.newphone.model.data.remote.list.Phone
import org.junit.Assert.*

import org.junit.Test

class MapperTest {

    @Test
    fun Phone.transformToEntity(): PhoneEntity =
        PhoneEntity(this.id, this.name, this.price, this.image)

    @Test
    fun transformToDetailEntity() {
    }
}