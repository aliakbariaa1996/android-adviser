package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.advisor.entity.AdviserTime
import ir.org.tavanesh.data.models.AdvisersFreeTimeModel

fun AdvisersFreeTimeModel.toAdviserFreeTime(): AdviserTime {
    return AdviserTime(
        id = this.id,
        time = "${this.timeFrom}-${this.timeTo}",
        weekday = this.weekday,
    )
}

fun List<AdvisersFreeTimeModel>.toAdviserFreeTime(): List<AdviserTime> {
    val list = mutableListOf<AdviserTime>()
    this.let {
        it.forEach { item ->
            list.add(item.toAdviserFreeTime())
        }
    }
    return list
}