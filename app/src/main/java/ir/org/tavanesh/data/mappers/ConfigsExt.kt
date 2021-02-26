package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.user.entity.Configs
import ir.org.tavanesh.core.domain.user.entity.Education
import ir.org.tavanesh.core.domain.user.entity.Gender
import ir.org.tavanesh.data.models.ConfigsResponse
import ir.org.tavanesh.data.models.EnumConfigResponse


fun ConfigsResponse.toConfigs(): Configs {
    return Configs(
        educations = this.educations.toEducation(),
        genders = this.genders.toGender(),
        appVersionInfo = null,
    )
}

fun EnumConfigResponse.toEducation(): Education {
    return Education(
        id = this.id,
        key = this.key,
        name = this.name,
    )
}

fun List<EnumConfigResponse>.toEducation(): List<Education> {
    val list = mutableListOf<Education>()
    this.let {
        it.forEach { item ->
            list.add(item.toEducation())
        }
    }
    return list
}

fun EnumConfigResponse.toGender(): Gender {
    return Gender(
        id = this.id,
        key = this.key,
        name = this.name,
    )
}

fun List<EnumConfigResponse>.toGender(): List<Gender> {
    val list = mutableListOf<Gender>()
    this.let {
        it.forEach { item ->
            list.add(item.toGender())
        }
    }
    return list
}



fun List<Gender>.toGenderNames():List<String>{
    val list = mutableListOf<String>()
    this.let {
        it.forEach { item ->
            list.add(item.name)
        }
    }
    return list
}



fun List<Education>.toEducationNames():List<String>{
    val list = mutableListOf<String>()
    this.let {
        it.forEach { item ->
            list.add(item.name)
        }
    }
    return list
}