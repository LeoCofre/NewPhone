package cl.awakelab.newphone.model.data.remote

import cl.awakelab.newphone.model.data.local.detail.PhoneDetailEntity
import cl.awakelab.newphone.model.data.local.list.PhoneEntity
import cl.awakelab.newphone.model.data.remote.detail.PhoneDetail
import cl.awakelab.newphone.model.data.remote.list.Phone

fun Phone.transformToEntity(): PhoneEntity =
    PhoneEntity(this.id, this.image, this.name, this.price)

fun PhoneDetail.transformToDetailEntity(id: Long): PhoneDetailEntity =
    PhoneDetailEntity(
        this.id,
        this.name,
        this.price,
        this.image,
        this.description,
        this.lastPrice,
        this.credit
    )