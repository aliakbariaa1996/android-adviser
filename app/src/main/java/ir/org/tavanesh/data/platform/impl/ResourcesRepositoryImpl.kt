package ir.org.tavanesh.data.platform.impl

import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.org.tavanesh.data.platform.datasource.ResourcesRepository
import javax.inject.Inject

class ResourcesRepositoryImpl @Inject constructor (
    @ApplicationContext private val context: Context,
) : ResourcesRepository {

    override fun isInternetConnected(): Boolean {
        var isConnected = false
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfoMobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val netInfoWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (netInfoMobile != null && netInfoMobile.isConnected) isConnected = true
        if (netInfoWifi != null && netInfoWifi.isConnected) isConnected = true

        return isConnected
    }

    override fun isLocationEnabled(): Boolean {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        var isGPSEnable = false
        try {
            isGPSEnable = lm!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return isGPSEnable
    }

    override fun hasRightGooglePlayVersion(): Boolean {
        return (GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS)
    }
}