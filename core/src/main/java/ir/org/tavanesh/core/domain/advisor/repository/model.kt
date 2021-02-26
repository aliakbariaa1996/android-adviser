package ir.org.tavanesh.core.domain.advisor.repository

import java.io.Serializable

data class GetAdviserFreeTimesByDataParams(
    var adviserId: String,
    var date: String,
)

data class GetAdviserParams(
    var adviserId: String,
)

data class GetAdvisersParams(
    var page: Int = 1,
    var adviceCenterId: String,
)

data class GetAdviceCentersParams(
    var page: Int = 1,
    var cityId: Int,
    var provinceId: Int,
    var text:String="",
):Serializable


data class GetAdviceCenterParams(
    var adviceCenterId: String,
):Serializable