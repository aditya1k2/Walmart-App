package com.example.walmart.module

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.walmart.R
import com.example.walmart.data.db.ProductDatabase
import com.example.walmart.ui.screens.PastOrdersActivity
import com.example.walmart.util.App
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WalmartModule : Application() {


    private val productDao = ProductDatabase.getDatabase(App.getAppContext()).getProductDao()

    private val baseURL = "https://api.walmartlabs.com/"
    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
    }

    val retrofitService by lazy {
        retrofit()
    }

//    fun getAppContext():Application{
//        return baseContext
//    }

    fun notification(context:Context) {
        val notificationChannel: NotificationChannel =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel("1", "Walmart", NotificationManager.IMPORTANCE_DEFAULT)
            } else {
                TODO("VERSION.SDK_INT < O")
            }

        notificationChannel.description = "Walmart"
        notificationChannel.setShowBadge(true) //by default it is true


        val notificationManagerCompat: NotificationManagerCompat =
            NotificationManagerCompat.from(context)
        notificationManagerCompat.createNotificationChannel(notificationChannel)

        //creating pending intent
        val intent = Intent(context, PastOrdersActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        //creating notification builder
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, "1")
        //setting notification properties
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
        builder.setContentIntent(pendingIntent)
        builder.setContentTitle("Walmart")
        builder.setContentText("Item Purchased")
        builder.setChannelId("1")

        //triggering notification
        notificationManagerCompat.notify(1, builder.build())
    }
}