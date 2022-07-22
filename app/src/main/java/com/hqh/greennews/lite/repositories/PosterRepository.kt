package com.hqh.greennews.lite.repositories

import androidx.annotation.WorkerThread
import com.hqh.greennews.lite.model.Poster
import com.hqh.greennews.lite.network.ArticleService
import com.hqh.greennews.lite.persistence.PosterDao
import com.hqh.greennews.viewmodels.PostsFeed
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class PosterRepository @Inject constructor(
    private val articleService: ArticleService,
    private val posterDao: PosterDao
){

    init {
        Timber.d("Injection MainRepository")
    }

    @WorkerThread
    fun loadArticlePosters(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val posters: List<Poster> = posterDao.getPosterList()
        if (posters.isEmpty()) {
            // request API network call asynchronously.
            articleService.getArticleLastest()
                // handle the case when the API request gets a success response.
                .suspendOnSuccess {
                    posterDao.insertPosterList(data)
                    emit(data)
                }
                // handle the case when the API request is fails.
                // e.g. internal server error.
                .onFailure { onError(message()) }
        } else {
            emit(posters)
        }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

}