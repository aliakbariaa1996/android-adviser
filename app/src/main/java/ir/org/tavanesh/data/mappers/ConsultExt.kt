package ir.org.tavanesh.data.mappers

import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.domain.consult.entity.ConsultStatues
import ir.org.tavanesh.data.models.ConsultModel
import ir.org.tavanesh.data.models.SubmitConsultModel

fun ConsultModel.toConsult(): Consult {
    return Consult(
        id = this.id,
        title = this.center.name,
        description = this.center.description,
        centerName = this.center.name,
        phone = this.center.phoneNumbers,
        latitude = this.center.latitude,
        longitude = this.center.longitude,
        address = this.center.address,
        adviserName = "${this.advisor.firstName} ${this.advisor.lastName}",
        status = ConsultStatues.valueOf(this.status),
        date = this.date,
        time = "${this.weekday.weekday} ${this.weekday.timeFrom} ${this.weekday.timeTo}",
    )
}

fun SubmitConsultModel.toConsult(): Consult {
    return Consult(
        id = this.id,
        title = "",
        description = "",
        centerName = "",
        phone = "",
        latitude = 0.0,
        longitude = 0.0,
        address = "",
        adviserName = "",
        status = ConsultStatues.valueOf(this.status),
        date = this.date,
        time = this.advisorWeekdayTimeId,
    )
}

fun List<ConsultModel>.toConsult(): List<Consult> {
    val list = mutableListOf<Consult>()
    this.let {
        it.forEach { item ->
            list.add(item.toConsult())
        }
    }
    return list
}