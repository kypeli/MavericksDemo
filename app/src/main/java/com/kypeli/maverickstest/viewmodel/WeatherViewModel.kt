package com.kypeli.maverickstest.viewmodel

import com.airbnb.mvrx.MavericksViewModel
import com.kypeli.maverickstest.repository.WeatherRepository
import com.kypeli.maverickstest.state.Weather

class WeatherViewModel(initial: Weather) : MavericksViewModel<Weather>(initial) {
    private val repo = WeatherRepository()

    fun temperature() = repo.fetchTemperature()
        .execute { copy(temperature = it) }

    fun sky() = repo.fetchSky()
        .execute { copy(sky = it) }
}