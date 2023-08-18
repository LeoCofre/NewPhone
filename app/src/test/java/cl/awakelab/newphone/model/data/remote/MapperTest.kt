package cl.awakelab.newphone.model.data.remote

import cl.awakelab.newphone.model.data.remote.detail.PhoneDetail
import cl.awakelab.newphone.model.data.remote.list.Phone
import org.junit.Assert.assertEquals
import org.junit.Test


class MapperTest {

    @Test
    fun transformToEntity() {
        //Given
        val phone = Phone(1, "www.prueba.cl", "PhoneTest", 12345)

        //When
        val resultTranform = phone.transformToEntity()

        //Then
        assertEquals(phone.id, resultTranform.id)
        assertEquals(phone.price, resultTranform.price)
        assertEquals(phone.name, resultTranform.name)
        assertEquals(phone.image, resultTranform.image)

    }

    @Test
    fun transformToDetailEntity() {

        //Given
        val phoneDetail =
            PhoneDetail(1, "PhoneTest", 1234, "www.prueba.cl", "Description", 4321, false)

        //When
        val resultTransformDetail = phoneDetail.transformToDetailEntity()

        //Then
        assertEquals(phoneDetail.id, resultTransformDetail.id)
        assertEquals(phoneDetail.name, resultTransformDetail.name)
        assertEquals(phoneDetail.price, resultTransformDetail.price)
        assertEquals(phoneDetail.image, resultTransformDetail.image)
        assertEquals(phoneDetail.description, resultTransformDetail.description)
        assertEquals(phoneDetail.lastPrice, resultTransformDetail.lastPrice)
        assertEquals(phoneDetail.credit, resultTransformDetail.credit)

    }
}