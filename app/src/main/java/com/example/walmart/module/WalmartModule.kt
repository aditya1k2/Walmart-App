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
import com.example.walmart.data.DataSource.WalmartDataSource
import com.example.walmart.data.db.ProductDatabase
import com.example.walmart.data.networkfactory.ApiInterface
import com.example.walmart.data.repository.WalmartRepository
import com.example.walmart.domain.CartUseCase
import com.example.walmart.domain.GetCategoryListDataUseCase
import com.example.walmart.domain.GetCategoryListUseCase
import com.example.walmart.domain.PastOrderUseCase
import com.example.walmart.ui.screens.PastOrdersActivity
import com.example.walmart.util.App
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WalmartModule {

    private val productDao by lazy {
        ProductDatabase.getDatabase(App.getAppContext()).getProductDao()
    }
    private val baseURL = "https://api.walmartlabs.com/"
    private val apiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(ApiInterface::class.java)
    }

    private val dataSource by lazy {
        WalmartDataSource(apiInterface)
    }

    val repository by lazy {
        WalmartRepository(dataSource, productDao)
    }

    val cartUseCase by lazy {
        CartUseCase(repository)
    }

    val getCategoryListUseCase by lazy {
        GetCategoryListUseCase(repository)
    }

    val getCategoryListDataUseCase by lazy {
        GetCategoryListDataUseCase(repository)
    }

    val pastOrderUseCase by lazy {
        PastOrderUseCase(repository)
    }


    fun notification(context: Context, channel: String) {
        val notificationChannel: NotificationChannel =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel(channel, "Walmart", NotificationManager.IMPORTANCE_DEFAULT)
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
        builder.setChannelId(channel)

        //triggering notification
        notificationManagerCompat.notify(channel.toInt(), builder.build())
    }
}