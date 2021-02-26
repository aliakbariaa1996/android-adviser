package ir.org.tavanesh.core.domain.consult.repository


data class GetConsultingInfoParams(
    var consultId: String,
)

data class GetConsultingHistoryParams(
    var page: Int = 1,
)

data class SubmitConsultingParams(
    var date: String,
    var timeId: String,
    var adviserId: String,
    var centerId: String,
)
