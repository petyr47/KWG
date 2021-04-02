package com.aneke.peter.kwg.startup

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.aneke.peter.kwg.ui.theme.KWGTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.aneke.peter.kwg.posts.PostsActivity
import com.aneke.peter.kwg.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KWGTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                        Splash()
                }
            }
        }
    }


    @Composable
    fun Splash() {
        val image = painterResource(id = R.drawable.kwg_logo_square_blue_bg)
        val modifier = Modifier.fillMaxSize()
        Column(modifier = modifier,
            verticalArrangement = Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(image,"logo")
            Button( colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.onBackground,
                contentColor = MaterialTheme.colors.background),
                onClick = {
                startActivity(Intent(this@MainActivity, PostsActivity::class.java))
            }) {
                Text("View Posts")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        KWGTheme {
            Splash()
        }
    }
}





