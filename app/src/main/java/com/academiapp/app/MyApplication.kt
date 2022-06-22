package com.academiapp.app

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.academiapp.activities.WorkDModel.ProjectsDegree
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
        var tempdata: ProjectsDegree? = null
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val context: Context = MyApplication.applicationContext()
    }
}