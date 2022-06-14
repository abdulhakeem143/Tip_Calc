package com.example.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalc.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.cos

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_15_percent -> 0.15
            R.id.option_18_percent -> 0.18
            else -> 0.20
        }
        //ignoring Null Value from cost of service text field
        if(cost == null){
            binding.tipResult.text = ""
            return
        }
        var tip = tipPercentage * cost
        if(binding.roundUpSwich.isChecked){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}