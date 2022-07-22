package com.hqh.greennews.ui.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.hqh.greennews.utils.CompletePreviews
import com.hqh.greennews.R
import com.hqh.greennews.lite.model.Poster
import com.hqh.greennews.ui.theme.GreenNewsTheme

@Composable
fun PostCardTop(post: Poster, modifier: Modifier = Modifier){
    val typography = MaterialTheme.typography
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val imageModifier = Modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
        Image(
            painter = rememberAsyncImagePainter(post.image),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))

        Text(
            text = post.title,
            style = typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = post.sourceName,
            style = typography.subtitle2,
            modifier = Modifier.padding(bottom = 2.dp)
        )

        CompositionLocalProvider( LocalContentAlpha provides ContentAlpha.medium ){
            Text(
                text = stringResource(
                    id = R.string.home_post_min_read,
                    formatArgs = arrayOf(
                        post.sourceName,
                        post.crawlTime
                    )
                ),
                style = typography.subtitle2
            )
        }
    }
}

//@Preview
//@Composable
//fun PostCardTopPreview() {
//    GreenNewsTheme {
//        Surface {
//            PostCardTop(posts.highlightedPost)
//        }
//    }
//}
//
//@CompletePreviews
//@Composable
//fun PostCardTopPreviews() {
//    GreenNewsTheme {
//        Surface {
//            PostCardTop(posts.highlightedPost)
//        }
//    }
//}