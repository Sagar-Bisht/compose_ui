package com.example.jetpack_compose.ui.screens.b

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DollarCount() {
    val counter = remember {
        mutableStateOf(1)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "$${counter.value*100}", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.size(180.dp))
        CustomButton{
            counter.value++
        }
    }
}

@Composable
private fun CustomButton(onClick : () -> Unit) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .clickable {
                onClick.invoke()
            },
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = Color.Yellow) ,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text("Tap", style = MaterialTheme.typography.headlineSmall)
        }
    }
}