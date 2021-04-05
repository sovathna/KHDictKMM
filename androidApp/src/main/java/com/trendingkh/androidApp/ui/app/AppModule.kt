package com.trendingkh.androidApp.ui.app

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  fun httpClient() = HttpClient()

}