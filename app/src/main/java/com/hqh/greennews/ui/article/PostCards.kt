package com.hqh.greennews.ui.article

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hqh.greennews.R
import com.hqh.greennews.lite.model.Poster
import com.hqh.greennews.ui.theme.GreenNewsTheme
import coil.compose.rememberAsyncImagePainter

@Composable
fun AuthorAndReadTime(
    post: Poster,
    modifier: Modifier = Modifier
) {
    Row(modifier){
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = stringResource(
                    id = R.string.home_post_min_read ,
                    formatArgs = arrayOf(
                        post.sourceName,
                        post.crawlTime
                    )
                )
                ,style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun PostImage(post: Poster, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(post.image),
        contentDescription = null,
        modifier = modifier
            .size(40.dp, 40.dp)
            .clip(MaterialTheme.shapes.small)
    )
}

@Composable
fun PostTitle(post: Poster){
    Text(post.title, style = MaterialTheme.typography.subtitle2)
}

@Composable
fun PostCardSimple(
    post: Poster,
    navigateToArticle: (String) -> Unit,
) {
//    val bookmarkAction = stringResource(if (isFavorite) R.string.unbookmark else R.string.bookmark)
    Row(
        modifier = Modifier
            .clickable(onClick = { navigateToArticle(post.id.toString()) })
            .padding(10.dp)
//            .semantics {
//                customActions = listOf(
//                    CustomAccessibilityAction(
//                        label = bookmarkAction,
//                        action = { onToggleFavorite(); true }
//                    )
//                )
//            }
    ){
        PostImage(post, Modifier.padding(end = 16.dp))
        Column(modifier = Modifier.weight(1f)){
            PostTitle(post)
            AuthorAndReadTime(post)
        }
//        Book
    }
}

@Composable
fun PostCardHistory(post: Poster, navigateToArticle: (String) -> Unit) {
    var openDialog by remember { mutableStateOf(false) }

    Row(
        Modifier
            .clickable(onClick = { navigateToArticle(post.id.toString()) })
    ){
        PostImage(
            post = post,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )
        Column(
            Modifier
                .weight(1f)
                .padding(top = 16.dp, bottom = 16.dp)
        ){
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = stringResource(id = R.string.home_post_based_on_history),
                    style = MaterialTheme.typography.overline
                )
            }
            PostTitle(post = post)
            AuthorAndReadTime(
                post = post,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            IconButton(onClick = { openDialog = true }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = stringResource(R.string.cd_more_actions)
                )
            }
        }
    }

    if(openDialog){
        AlertDialog(
            modifier = Modifier.padding(20.dp),
            onDismissRequest = {openDialog = false},
            title = {
                Text(
                    text = stringResource(id = R.string.fewer_stories),
                    style = MaterialTheme.typography.h6
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.fewer_stories_content),
                    style = MaterialTheme.typography.body1
                )
            },
            confirmButton = {
                Text(
                    text = stringResource(id = R.string.agree),
                    style = MaterialTheme.typography.button,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable { openDialog = false }
                )
            }
        )
    }
}

//@Preview("Bookmark Button")
//@Composable
//fun BookmarkButtonPreview() {
//    GreenNewsTheme {
//        Surface {
//            BookmarkButton(isBookmarked = false, onClick = { })
//        }
//    }
//}

//@Preview("Bookmark Button Bookmarked")
//@Composable
//fun BookmarkButtonBookmarkedPreview() {
//    GreenNewsTheme {
//        Surface {
//            BookmarkButton(isBookmarked = true, onClick = { })
//        }
//    }
//}

//@Preview("Simple post card")
//@Preview("Simple post card (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun SimplePostPreview() {
//    GreenNewsTheme {
//        Surface {
//            PostCardSimple(post3, {}, false, {})
//        }
//    }
//}
//
//@Preview("Post History card")
//@Composable
//fun HistoryPostPreview() {
//    GreenNewsTheme {
//        Surface {
//            PostCardHistory(post3, {})
//        }
//    }
//}

