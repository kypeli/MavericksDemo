package com.kypeli.maverickstest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.*
import com.kypeli.maverickstest.databinding.FragmentWeatherBinding
import com.kypeli.maverickstest.state.Weather
import com.kypeli.maverickstest.viewbinding.viewBinding
import com.kypeli.maverickstest.viewmodel.WeatherViewModel

class WeatherFragment : Fragment(R.layout.fragment_weather), MavericksView {
    private val binding: FragmentWeatherBinding by viewBinding(FragmentWeatherBinding::bind)
    private val viewModel: WeatherViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            update()
        }

        viewModel.onAsync(
            Weather::sky,
            onFail = { binding.sky.text = "Error" },
            onSuccess = { binding.sky.text = it }
        )
    }

    override fun onResume() {
        super.onResume()
        update()
    }

    override fun invalidate() = withState(viewModel) { state ->
        binding.temperature.text = when (state.temperature) {
            is Loading -> "Loading..."
            is Success -> "${state.temperature()} °C"
            is Fail -> "Failed!"
            else -> ""
        }
    }

    private fun update() {
        viewModel.temperature()
        viewModel.sky()
    }
}