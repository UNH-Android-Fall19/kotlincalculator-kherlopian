package edu.newhaven.krikorherlopian.android.kotlincalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var isEmpty: Boolean = true
    var resultVisible: Boolean = false
    var lastDot: Boolean = false
    var lastOperator: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onDigit(view: View) {
        //if empty start by number, else append to it.
        // If result of an operation is displayed, and new number clicked = Start a new operation.Clear old operation.
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


    fun onDecimalPoint(view: View) {
        //lastdot to make sure 2 dots not in same number like 3.445.333
        if (!isEmpty && !lastDot) {
            txtInput.append((view as Button).text)
            lastDot = true
            lastOperator = false

        } else if (isEmpty) {//if empty, user can start writing a dot like .2 which means 0.2.
            txtInput.text = (view as Button).text
            isEmpty = false
            lastDot = false
            lastOperator = false
            resultVisible = false
        }
    }


    fun onOperator(view: View) {
        if (!lastOperator) {
            var operator = (view as Button).text
            //if empty, and nothing entered yet. User can enter a + or  - number.But not multiply or divide.
            System.out.println(isEmpty )
            System.out.println("lllllllllll"+operator)
            if(isEmpty && (operator.equals("+") || operator.equals("-")))
                txtInput.text = (view as Button).text
            else if(!isEmpty)
                txtInput.append((view as Button).text)

            resultVisible = false
            lastDot = false
            lastOperator = true
            isEmpty = false
        }
    }


    fun onClear(view: View) {
        this.txtInput.text = ""
        isEmpty = true
        lastOperator = false
        lastDot = false
        resultVisible = false
    }


    fun onEqual(view: View) {
        val txt = txtInput.text.toString()
        val expression = ExpressionBuilder(txt).build()
        //put in catch, because if user divides with 0 application crashes. 3/0.
        try{
            val result = expression.evaluate()
            txtInput.text = result.toString()
        }
        catch(ex : ArithmeticException){
            txtInput.text = ""
        }
        resultVisible = true
        lastDot = true
        lastOperator = false
    }
}
