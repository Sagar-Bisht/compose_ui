package com.example.jetpack_compose.ui.a

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class DesignAActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
              ShowSurface()
        }
    }

    private fun showToast() {
        Toast.makeText(this, "button clicked", Toast.LENGTH_LONG).show()
    }


}

fun projectList(): List<Projects> {
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




