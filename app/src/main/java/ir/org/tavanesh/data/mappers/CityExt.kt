package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.data.models.CityEntity
import ir.org.tavanesh.data.models.CityResponse
import ir.org.tavanesh.data.models.ProvinceResponse


fun CityEntity.toCity(): City {
    return City(
        this.cityId,
        this.parentId,
        this.name,
    )
}

fun List<CityEntity>.toCity(): List<City>{
    val list = mutableListOf<City>()
    this.let {
        it.forEach { item ->
            list.add(item.toCity())
        }
    }
    return list
}

fun CityResponse.toCity(): City {
    return City(
        this.id,
        this.parentId,
        this.name,
    )
}

fun ProvinceResponse.toCity(): List<City> {
    val list = mutableListOf<City>()
    list.add(City(
        this.id,
        0,
        this.name,
    ))
    this.cities.forEach {
        list.add(it.toCity())
    }
    return list
}

fun List<ProvinceResponse>.toCities(): List<City>{
    val list = mutableListOf<City>()
    this.let {
        it.forEach { item ->
            list.addAll(item.toCity())
        }
    }
    return list
}

fun City.toCityEntity(): CityEntity {
    return CityEntity(
        cityId = this.id,
        parentId = this.parentId,
        name = this.name,
    )
}

fun List<City>.toCityEntity(): List<CityEntity>{
    val list = mutableListOf<CityEntity>()
    this.let {
        it.forEach { item ->
            list.add(item.toCityEntity())
        }
    }
    return list
}

fun List<City>.toNames(): List<String> {
    val list = mutableListOf<String>()
    this.let {
        it.forEach { item ->
            item.name.let { name ->
                list.add(name)
            }
        }
    }
    return list
}