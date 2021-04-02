package com.aneke.peter.kwg.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.aneke.peter.kwg.R
import com.aneke.peter.kwg.ui.theme.KWGTheme

class WebViewActivity : AppCompatActivity() {

    var showLoader =  mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KWGTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.onBackground) {
                    WebPageScreen()
                }
            }
        }
    }


    @Composable
    fun WebPageScreen() {
    Column() {
        TopAppBar()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),){
                WebView()
            }
            if (showLoader.value) {
                CircularProgressIndicator()
            }
        }
    }
    }


    @Composable
    fun WebView() {
        val urlToRender = "https://www.kwgsoftworks.com/"
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        showLoader.value = false
                    }
                }
                loadUrl(urlToRender)
                settings.javaScriptEnabled = true
            }
        }, update = {
            it.loadUrl(urlToRender)
        })
    }

    @Composable
    fun TopAppBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(color = MaterialTheme.colors.background),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val backIcon = painterResource(id = R.drawable.ic_back)
            Icon(
                painter = backIcon,
                tint = Color.White,
                contentDescription = "",
                modifier = Modifier
                    .clickable(onClick = { onBackPressed() })
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)

            )
    }
    }


    @Preview(showBackground = true)
    @Composable
    fun WebPreview() {
        KWGTheme {
            WebPageScreen()
        }
    }
}
