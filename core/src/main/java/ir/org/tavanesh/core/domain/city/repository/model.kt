package ir.org.tavanesh.core.domain.city.repository


data class GetAllCityProvincesParams(
    var page: Int? = null,
)

data class GetCitiesOfProvinceParams(
    var provinceId: Int,
)

data class GetProvincesParams(
    var page: Int? = null,
)
