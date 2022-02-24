package com.kypeli.maverickstest

import android.app.Application
import com.airbnb.mvrx.Mavericks

class MavericksApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}