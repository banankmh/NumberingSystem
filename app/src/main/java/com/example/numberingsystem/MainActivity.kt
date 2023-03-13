package com.example.numberingsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var buttonClean: Button
    private lateinit var buttonConvert: Button
    private lateinit var buttonToDecimal: Button
    private lateinit var buttonToBinary: Button
    private lateinit var buttonToOctal: Button
    private lateinit var buttonToHexaDecimal: Button
    private lateinit var enterdeNumber: EditText
    private lateinit var result: TextView
    private var number = 0.0
    private var currentNumberSystem: NumbringSystem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        addCallbacks()
    }

    private fun addCallbacks() {
        buttonClean.setOnClickListener {
            clearInput()
        }
        buttonToBinary.setOnClickListener {
            prepareOperation(NumbringSystem.ToBinary)
        }
        buttonToDecimal.setOnClickListener {
            prepareOperation(NumbringSystem.ToDecimal)
        }
        buttonToOctal.setOnClickListener {
            prepareOperation(NumbringSystem.ToOctal)
        }
        buttonToHexaDecimal.setOnClickListener {
            prepareOperation(NumbringSystem.ToHexa)
        }
        buttonConvert.setOnClickListener {
            doCurrentConversion()
        }
    }

    private fun doCurrentConversion() {
        val inputNumber = enterdeNumber.text.toString()
        if (inputNumber.isNotEmpty()) {

            number = inputNumber.toDouble()
            when (currentNumberSystem) {
                NumbringSystem.ToBinary -> result.text = convertNumber(number, NumbringSystem.ToDecimal, NumbringSystem.ToBinary)
                NumbringSystem.ToDecimal -> result.text = number.toString()
                NumbringSystem.ToOctal -> result.text = convertNumber(number, NumbringSystem.ToDecimal,  NumbringSystem.ToOctal)
                NumbringSystem.ToHexa -> result.text = convertNumber(number,  NumbringSystem.ToDecimal,  NumbringSystem.ToHexa)
                null -> result.text = "Please select a conversion option"
            }

        } else {
            result.text = "Please enter a number to convert"
        }
    }

    private fun convertNumber(inputValue: Double, inputSystem: NumbringSystem, outputSystem:NumbringSystem): String {
        val intValue = when (inputSystem) {
            NumbringSystem.ToDecimal-> inputValue.toLong()
            NumbringSystem.ToBinary-> Integer.parseInt(inputValue.toLong().toString(), 2).toLong()
            NumbringSystem.ToOctal-> Integer.parseInt(inputValue.toLong().toString(), 8).toLong()
            NumbringSystem.ToHexa-> Integer.parseInt(inputValue.toLong().toString(), 16).toLong()
            else -> 0
        }

        val outputValue = when (outputSystem) {
            NumbringSystem.ToDecimal-> intValue.toString()
            NumbringSystem.ToBinary-> java.lang.Long.toBinaryString(intValue)
            NumbringSystem.ToOctal-> java.lang.Long.toOctalString(intValue)
            NumbringSystem.ToHexa -> java.lang.Long.toHexString(intValue)
            else -> ""
        }

        return outputValue
    }

    private fun prepareOperation(numberSystem: NumbringSystem) {
        number = enterdeNumber.text.toString().toDouble()
        currentNumberSystem = numberSystem
    }

    private fun clearInput() {
        enterdeNumber.text = null
        result.text = ""
    }

    private fun initView() {
        buttonClean = findViewById(R.id.btn_clean)
        buttonConvert = findViewById(R.id.btn_Convert)
        buttonToDecimal = findViewById(R.id.btn_toDecimal)
        buttonToBinary = findViewById(R.id.btn_toBinary)
        buttonToOctal = findViewById(R.id.btn_toOctal)
        buttonToHexaDecimal = findViewById(R.id.btn_toHexaDecimal)
        enterdeNumber = findViewById(R.id.EntredNumber)
        result = findViewById(R.id.result)
    }
}
