package com.kypeli.maverickstest.state

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class Weather(
    val sky: Async<String> = Uninitialized,
    val temperature: Async<Int> = Uninitialized
) : MavericksState
