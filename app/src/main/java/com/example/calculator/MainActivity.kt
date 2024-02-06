package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {

    // declare variables
    private lateinit var editText: EditText
    private var currentInput: StringBuilder = StringBuilder()
    private var currentOperator: String? = null
    private var operand1: Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // find the id for editText and set it to the variable editText
        editText = findViewById(R.id.editText)

        // set click listener for number buttons (0-9)
        findViewById<Button>(R.id.zero_button).setOnClickListener {
            append("0")
        }
        findViewById<Button>(R.id.one_button).setOnClickListener {
            append("1")
        }
        findViewById<Button>(R.id.two_button).setOnClickListener {
            append("2")
        }
        findViewById<Button>(R.id.three_button).setOnClickListener {
            append("3")
        }
        findViewById<Button>(R.id.four_button).setOnClickListener{
            append("4")
        }
        findViewById<Button>(R.id.five_button).setOnClickListener{
            append("5")
        }
        findViewById<Button>(R.id.six_button).setOnClickListener{
            append("6")
        }
        findViewById<Button>(R.id.seven_button).setOnClickListener {
            append("7")
        }
        findViewById<Button>(R.id.eight_button).setOnClickListener{
            append("8")
        }
        findViewById<Button>(R.id.nine_button).setOnClickListener {
            append("9")
        }

        // set click listener for operators
        findViewById<Button>(R.id.add_button).setOnClickListener {
            operator("+")
        }
        findViewById<Button>(R.id.subtract_button).setOnClickListener {
            operator("-")
        }
        findViewById<Button>(R.id.multiply_button).setOnClickListener {
            operator("*")
        }
        findViewById<Button>(R.id.divide_button).setOnClickListener{
            operator("/")
        }
        findViewById<Button>(R.id.sqrt_button).setOnClickListener {
            if (currentOperator == null && operand1 == null) {
                val input = currentInput.toString().toDoubleOrNull()
                if (input != null) {
                    val result = sqrt(input)
                    editText.setText(result.toString())
                } else {
                    editText.setText("Invalid input")
                }
                currentInput.clear()
            } else {
                editText.setText("Invalid input")
                currentInput.clear()
                operand1 = null
                currentOperator = null
            }
        }
        findViewById<Button>(R.id.dot_button).setOnClickListener {
            append(".")
        }
        findViewById<Button>(R.id.equal_button).setOnClickListener {
            calculate()
        }
        findViewById<Button>(R.id.clear_button).setOnClickListener {
            currentInput.clear() // clear input
            operand1 = null // set operand to null
            currentOperator = null // set current operator to null
            editText.text.clear()
        }
    }

    private fun append(value: String) {
        currentInput.append(value)
        editText.setText(currentInput.toString())
    }

    private fun operator(operator: String) {
        if (operand1 == null) {
            operand1 = currentInput.toString().toDoubleOrNull()
            currentInput.clear()
            currentOperator = operator
        } else {
            calculate()
            currentOperator = operator
        }
    }

    private fun calculate() {
        val operand2 = currentInput.toString().toDoubleOrNull()

        if (operand1 != null && operand2 != null && currentOperator != null) {
            if (currentOperator == "/" && operand2 == 0.0) {
                // handle division by zero
                editText.setText("Divide by 0 Error")
                currentInput.clear()
                operand1 = null
                currentOperator = null
            } else {
                val result = if (currentOperator == "+") {
                    operand1!! + operand2
                } else if (currentOperator == "-") {
                    operand1!! - operand2
                } else if (currentOperator == "*") {
                    operand1!! * operand2
                } else if (currentOperator == "/") {
                    operand1!! / operand2
                } else {
                    throw IllegalArgumentException("Invalid Operator")
                }

                editText.setText(result.toString())
                currentInput.clear()
                operand1 = result
                currentOperator = null
            }
        }
    }
}