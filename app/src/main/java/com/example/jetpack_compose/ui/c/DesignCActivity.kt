package com.example.jetpack_compose.ui.c

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


class DesignCActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    fun MyApp() {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            TipCalculator()
        }
    }
}

fun getTipAmount(userAmount : String , tipPercentage : Float) : String {
    return when{
        userAmount.isEmpty() ->{
            "0"
        }
        else ->{
            val amount = userAmount.toFloat()
            (amount * tipPercentage.div(100)).toString()
        }
    }
}

fun getTotalAmount(userAmount: String , tipPercentage: Float , personCounter: Int) : String{
    return when {
        userAmount.isEmpty() ->{
            "0"
        }
        else ->{
            val amount = userAmount.toFloat()
            val tipAmount = amount * tipPercentage.div(100)
            val perHeadAmount = (amount + tipAmount).div(personCounter)
            perHeadAmount.toString()
        }
    }
}

fun formatTwoDecimalPoints(str : String) : String{
   return if(str.isEmpty()){
        ""
    }else{
        val amount = str.toFloat()
        return "%.2f".format(amount)
    }
}