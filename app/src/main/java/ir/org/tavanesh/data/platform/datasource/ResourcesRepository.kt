package ir.org.tavanesh.data.platform.datasource

interface ResourcesRepository {
    fun isInternetConnected(): Boolean

    fun isLocationEnabled(): Boolean

    fun hasRightGooglePlayVersion(): Boolean
}