package com.kypeli.maverickstest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.*
import com.kypeli.maverickstest.databinding.FragmentWeatherBinding
import com.kypeli.maverickstest.viewbinding.viewBinding
import com.kypeli.maverickstest.viewmodel.WeatherViewModel


class WeatherFragment : Fragment(R.layout.fragment_weather), MavericksView {
    private val binding: FragmentWeatherBinding by viewBinding(FragmentWeatherBinding::bind)
    private val viewModel: WeatherViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        update()
        binding.button.setOnClickListener {
            update()
        }
    }

    override fun invalidate() = withState(viewModel) {
        val temperatureValue = when (it.temperature) {
            is Loading -> "Loading..."
            is Success -> "${it.temperature()} °C"
            is Fail -> "Failed!"
            else -> ""
        }
        binding.temperature.text = temperatureValue

        val skyValue = when (it.sky) {
            is Loading -> "Loading..."
            is Success -> it.sky()
            is Fail -> "Failed!"
            is Uninitialized -> ""
        }
        binding.sky.text = skyValue
    }

    private fun update() {
        viewModel.temperature()
        viewModel.sky()
    }
}