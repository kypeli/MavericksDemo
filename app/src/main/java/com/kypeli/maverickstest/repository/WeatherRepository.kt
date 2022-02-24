package com.kypeli.maverickstest.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepository {
    fun fetchTemperature(): Flow<Int> =
        flow {
            delay(2000)
            emit(-5)
        }

    fun fetchSky(): Flow<String> =
        flow {
            delay(3500)
            emit("Sunny!")
        }
}