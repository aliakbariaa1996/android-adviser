package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.advisor.entity.AdviceCenter
import ir.org.tavanesh.data.models.AdviceCenterModel

fun AdviceCenterModel.toAdviceCenter(): AdviceCenter {
    return AdviceCenter(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.logo,
        address = this.address,
        latitude = this.latitude,
        longitude = this.longitude,
        adviserCount = this.advisorCount,
    )
}

fun List<AdviceCenterModel>.toAdviceCenter(): List<AdviceCenter> {
    val list = mutableListOf<AdviceCenter>()
    this.let {
        it.forEach { item ->
            list.add(item.toAdviceCenter())
        }
    }
    return list
}