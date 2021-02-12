package com.example.walmart.util

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        private lateinit var context: Context;

        fun getAppContext(): Context {
            return context;
        }

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}