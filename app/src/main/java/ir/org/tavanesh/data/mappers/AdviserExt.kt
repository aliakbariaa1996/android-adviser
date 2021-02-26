package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.advisor.entity.Adviser
import ir.org.tavanesh.data.models.AdviserModel

fun AdviserModel.toAdviser(): Adviser {
    return Adviser(
        id = this.id,
        lastName = this.lastName,
        firstName = this.firstName,
        description = this.description,
        avatar = this.avatar,
    )
}

fun List<AdviserModel>.toAdviser(): List<Adviser> {
    val list = mutableListOf<Adviser>()
    this.let {
        it.forEach { item ->
            list.add(item.toAdviser())
        }
    }
    return list
}