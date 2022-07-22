package com.hqh.greennews.lite.module

import com.hqh.greennews.lite.network.ArticleService
import com.hqh.greennews.lite.persistence.PosterDao
import com.hqh.greennews.lite.repositories.PosterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        articleService: ArticleService,
        posterDao: PosterDao
    ): PosterRepository {
        return PosterRepository(articleService, posterDao)
    }
}