package com.example.jetpack_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //  ShowSurface() 
            DollarCount()
        }
    }

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

    private fun showToast() {
        Toast.makeText(this, "button clicked", Toast.LENGTH_LONG).show()
    }

    @Composable
    fun ShowSurface() {

        val isProjectVisible = remember {
            mutableStateOf(false)
        }

        Surface(
            shadowElevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.profile), contentDescription = ""
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "Sagar Singh bisht",
                    style = TextStyle(
                        fontWeight = FontWeight(500),
                        fontSize = 20.sp,
                        color = Color.Green
                    ),
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Android Compose Developer",
                    style = TextStyle(
                        fontWeight = FontWeight(500),
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                )
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = ""
                    )
                    Text(
                        text = "Instagram",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp)
                    )
                }
                Spacer(modifier = Modifier.size(5.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = ""
                    )
                    Text(
                        text = "FaceBook",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp)
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Button(onClick = {
                    isProjectVisible.value = !isProjectVisible.value
                }) {
                    Text(text = "My Projects")
                }
                Spacer(modifier = Modifier.size(20.dp))

                if (isProjectVisible.value) {
                    LazyColumn {
                        items(projectList()) {
                            ProjectItem(project = it)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ProjectItem(project: Projects) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = project.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = project.desc,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }


    private fun projectList(): List<Projects> {
        return listOf(
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
            Projects(name = "Social Media", desc = "desc"),
        )
    }

    data class Projects(
        val name: String,
        val desc: String
    )
}




