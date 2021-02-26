package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.core.domain.logy.entity.Logy
import ir.org.tavanesh.data.models.CityEntity
import ir.org.tavanesh.data.models.LogyEntity

fun LogyEntity.toLogy(): Logy {
    return Logy(
        "",
    )
}

fun List<LogyEntity>.toLogy(): List<Logy>{
    val list = mutableListOf<Logy>()
    this.let {
        it.forEach { item ->
            list.add(item.toLogy())
        }
    }
    return list
}

fun Logy.toLogy(): LogyEntity {
    return LogyEntity(
        type = "",
        date = "",
        duration = 0,
    )
}