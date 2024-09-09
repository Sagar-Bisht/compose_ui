package com.example.jetpack_compose.ui.screens.a

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose.R


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
