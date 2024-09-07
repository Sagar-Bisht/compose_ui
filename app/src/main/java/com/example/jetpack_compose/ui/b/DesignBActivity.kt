package com.example.jetpack_compose.ui.b

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class DesignBActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DollarCount()
        }
    }
}