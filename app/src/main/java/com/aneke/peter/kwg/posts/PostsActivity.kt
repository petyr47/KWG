package com.aneke.peter.kwg.posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.aneke.peter.kwg.PostItem
import com.aneke.peter.kwg.R
import com.aneke.peter.kwg.webview.WebViewActivity
import com.aneke.peter.kwg.ui.theme.KWGTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModel()

    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KWGTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.LightGray) {
                    PostScreen()
                }
            }
        }

    }

    @ExperimentalPagingApi
    @Composable
    fun PostScreen() {
        val lazyPostItems = viewModel.posts.collectAsLazyPagingItems()
        var count = 0
        Column{
            TopAppBar()
            LazyColumn {
                items(lazyPostItems) { post ->
                        Log.e("Post counter", "${count++}")
                        PostItem(post = post!!)
                }
            }
        }
    }


    @ExperimentalPagingApi
    @Preview(showBackground = true)
    @Composable
    fun PostsPreview() {
        KWGTheme {
            PostScreen()
        }
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
            val webIcon = painterResource(id = R.drawable.ic_web)
            Icon(
                painter = backIcon,
                tint = Color.White,
                contentDescription = "",
                modifier = Modifier
                    .clickable(onClick = { onBackPressed() })
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)

            )

            Text(
                text = "Posts",
                style = MaterialTheme.typography.h6,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )


            Icon(
                painter = webIcon,
                tint = Color.White,
                contentDescription = "",
                modifier = Modifier
                    .clickable(onClick = {
                        startActivity(Intent(this@PostsActivity, WebViewActivity::class.java))
                    })
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)

            )
        }
    }


}




