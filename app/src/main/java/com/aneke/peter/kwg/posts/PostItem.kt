package com.aneke.peter.kwg

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aneke.peter.kwg.data.Post
import com.aneke.peter.kwg.ui.theme.KWGTheme

@Composable
fun PostItem(post : Post) {
    val modifier = Modifier
        .clickable(onClick = {})
        .padding(16.dp)
        .fillMaxWidth()

    Card(modifier = modifier,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding( 8.dp)
        ) {
            Log.e("Post", post.toString())
            PostName(post.name)
            PostEmail(post.email)
            PostBody(post.body)
        }
    }
}

@Composable
fun PostName( title : String) {
    Text(
        text = title,
        maxLines = 1,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
fun PostEmail( email : String) {
    Text(
        text = email,
        maxLines = 1,
        style = MaterialTheme.typography.subtitle1,
        overflow = TextOverflow.Ellipsis,
        color = Color.Gray,
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
fun PostBody(details : String) {
    Text(
        text = details,
        maxLines = 7,
        style = MaterialTheme.typography.body2,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(4.dp)
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ItemPreview() {
    KWGTheme {
        PostItem(Post(1, 1, 1, "Peter Aneke", "anekepeter47@gmail.com", "u wufbwf wbfhbfwblfh lwfbi bwf wbfhwbf hwf"))
    }
}


