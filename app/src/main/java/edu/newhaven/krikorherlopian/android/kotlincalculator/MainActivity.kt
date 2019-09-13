package edu.newhaven.krikorherlopian.android.kotlincalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    // lateinit var txtInput: TextView

    var isEmpty: Boolean = true
    var resultVisible: Boolean = false
    var lastDot: Boolean = false
    var lastOperator: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Append the Button.text to the TextView
     */
    fun onDigit(view: View) {
        if (isEmpty || resultVisible) {
            txtInput.text = (view as Button).text
            isEmpty = false
            lastDot = false
            lastOperator = false
            resultVisible = false
        } else {
            isEmpty = false
            txtInput.append((view as Button).text)
            lastOperator = false
        }

    }

    /**
     * Append . to the TextView
     */
    fun onDecimalPoint(view: View) {
        if (!isEmpty && !lastDot) {
            txtInput.append((view as Button).text)
            lastDot = true
            lastOperator = false

        } else if (isEmpty) {
            txtInput.text = (view as Button).text
            isEmpty = false
            lastDot = false
            lastOperator = false
            resultVisible = false
        }
    }

    /**
     * Append +,-,*,/ operators to the TextView
     */
    fun onOperator(view: View) {
        if (!isEmpty && !lastOperator) {
            txtInput.append((view as Button).text)
            resultVisible = false
            lastDot = false
            lastOperator = true
        }
    }


    /**
     * Clear the TextView
     */
    fun onClear(view: View) {
        this.txtInput.text = ""
        isEmpty = true
        lastOperator = false
        lastDot = false
        resultVisible = false
    }

    /**
     * Calculate the output using Exp4j
     */
    fun onEqual(view: View) {
        val txt = txtInput.text.toString()
        val expression = ExpressionBuilder(txt).build()
        val result = expression.evaluate()
        txtInput.text = result.toString()
        resultVisible = true
        lastDot = true
        lastOperator = false
    }
}
