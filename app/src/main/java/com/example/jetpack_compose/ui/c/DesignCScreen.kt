package com.example.jetpack_compose.ui.c

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TipCalculator() {

    val amount = remember {
        mutableStateOf("")
    }

    val personCounter = remember {
        mutableStateOf(1)
    }

    val tipPercentage = remember {
        mutableStateOf(0f)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        TopHeader(amount = getTotalAmount(
            userAmount = amount.value ,
            tipPercentage = tipPercentage.value ,
            personCounter = personCounter.value
        )
        )
        UserInputArea(amount = amount.value,
            amountChanged = {
                amount.value = it
            },
            personCounter = personCounter.value,
            onAddOrReducePerson = {
                if(it < 0){
                    if(personCounter.value != 1){
                        personCounter.value--
                    }
                }else{
                    personCounter.value++
                }
            },
            tipPercentage = tipPercentage.value,
            tipPercentageChanged = {
                tipPercentage.value = it
            }
        )
    }
}

@Composable
fun TopHeader(amount: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Blue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = "Total per person", style = TextStyle(
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "$${formatTwoDecimalPoints(amount)}", style = TextStyle(
                    color = Color.White, fontWeight = FontWeight.Medium, fontSize = 20.sp
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
fun UserInputArea(
    amount: String,
    amountChanged: (String) -> Unit,
    personCounter: Int,
    onAddOrReducePerson: (Int) -> Unit,
    tipPercentage: Float,
    tipPercentageChanged: (Float) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shadowElevation = 12.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = amount,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    amountChanged.invoke(it)
                },
                placeholder = { Text(text = "Enter Your Amount") },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                })
            )
            Spacer(modifier = Modifier.height(8.dp))
            if(amount.isNotEmpty()){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Split", style = TextStyle(
                            color = Color.Black, fontWeight = FontWeight.Normal, fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(.55f))
                    CustomButtons(imageVector = Icons.Default.KeyboardArrowUp, onClick = {
                        onAddOrReducePerson.invoke(1)
                    })
                    Text(
                        text = "$personCounter",
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 7.dp)
                    )
                    CustomButtons(imageVector = Icons.Default.KeyboardArrowDown, onClick = {
                        onAddOrReducePerson.invoke(-1)
                    })
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tip",
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(.60f))
                    Text(
                        text = "$ ${formatTwoDecimalPoints(getTipAmount(userAmount = amount ,tipPercentage= tipPercentage))}",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = "${formatTwoDecimalPoints(tipPercentage.toString())}%",
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Slider(
                    value = tipPercentage,
                    onValueChange = {
                        tipPercentageChanged.invoke(it)
                    },
                    valueRange = 0f..100f,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun CustomButtons(
    imageVector: ImageVector, onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp)
            .clickable {
                onClick.invoke()
            }, shape = CircleShape

    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .padding(4.dp)
        )
    }
}