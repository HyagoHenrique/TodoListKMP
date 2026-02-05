package com.example.todolistkmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolistkmp.android.AndroidAppModule
import com.example.todolistkmp.android.TodoScreen


class MainActivity : ComponentActivity() {
    private lateinit var androidAppModule: AndroidAppModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        androidAppModule = AndroidAppModule(applicationContext)
        setContent {
            MaterialTheme {
                Surface {
                    TodoScreen(controller = androidAppModule.appModule.todoController)
                }
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {

}