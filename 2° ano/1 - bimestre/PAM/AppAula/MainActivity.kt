package com.example.appaula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Applier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appaula.ui.theme.AppAulaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppAulaTheme {

                }
            }
        }
    }
@Composable
fun App(){
    Column { 
        Row (
            Modifier
                .padding(35)
        ){ 
            Text("Linha 1")
        }
        Row { 
            Text("Linha 2")
        }
        Row { "Linha3" }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}