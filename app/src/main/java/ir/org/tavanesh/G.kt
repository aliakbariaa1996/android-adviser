package ir.org.tavanesh

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp
import ir.org.tavanesh.data.platform.engine.DataBase
import timber.log.Timber

@HiltAndroidApp
class G : Application() {

    companion object {
        lateinit var database: DataBase
        var isFirstExam = false
    }

    override fun onCreate() {
        super.onCreate()

        database = DataBase.getInstance(applicationContext)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        createChannel(
            getString(R.string.default_notification_channel_id),
            getString(R.string.notification_alarm)
        )

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setShowBadge(true)
            }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.notification_alarm)

            val notificationManager = this.getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }
}