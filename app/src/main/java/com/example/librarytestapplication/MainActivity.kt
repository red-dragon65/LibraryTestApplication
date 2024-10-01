package com.example.librarytestapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.custommathlib.CalculateSum
import com.example.supercalc.Strategies.Average
import com.example.supercalc.CustomCalculator

class MainActivity : AppCompatActivity() {

    // Hold UI elements
    // Note: 'lateinit' means the variable will get initialized later (later means in the onCreate function)
    private lateinit var inputNumberOne: EditText
    private lateinit var inputNumberTwo: EditText
    private lateinit var textViewResult: TextView
    private lateinit var buttonCalc: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Grab the UI elements
        inputNumberOne = findViewById(R.id.inputNumberOne)
        inputNumberTwo = findViewById(R.id.inputNumberTwo)
        textViewResult = findViewById(R.id.textViewResult)
        buttonCalc = findViewById(R.id.buttonCalc)

        // Set the listener for the button
        buttonCalc.setOnClickListener {

            calculateSumFromFields()
        }

    }

    private fun calculateSumFromFields(){

        // Get the input numbers
        var numOne = 0
        var numTwo = 0

        // Avoid shitting the bed
        if(inputNumberTwo.text.toString().isNotEmpty() || inputNumberTwo.text.toString().isNotEmpty()){

            numOne = inputNumberOne.text.toString().toInt()
            numTwo = inputNumberTwo.text.toString().toInt()
        }


        // This uses our custom library to calculate the sum!!!
        val calculator = CalculateSum()
        val output: Int = calculator.calculateSum(numOne, numTwo)

        // Test the second library
        testSecondLibrary()


        // Display the result
        val resultText = "The sum of $numOne + $numTwo is $output"
        textViewResult.text = resultText

        // Reset the input fields
        inputNumberOne.setText("")
        inputNumberTwo.setText("")
    }

    private fun testSecondLibrary(){

        val defaultCalc = CustomCalculator()

        val numbers = listOf(1, 3, 8, 12, 43)
        val defaultResult = defaultCalc.gigaCalc(numbers)


        val betterCalc = CustomCalculator(Average())
        val betterResult = betterCalc.gigaCalc(numbers)

        Log.d("Calculation test", "Default Result: $defaultResult")
        Log.d("Calculation test", "Better Result: $betterResult")

    }
}