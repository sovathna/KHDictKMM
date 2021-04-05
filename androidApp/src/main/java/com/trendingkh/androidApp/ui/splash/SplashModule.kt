package com.trendingkh.androidApp.ui.splash

import com.trendingkh.shared.DownloadService
import com.trendingkh.shared.SplashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.*


@Module
@InstallIn(ViewModelComponent::class)
class SplashViewModelModule {

  @Provides
  @ViewModelScoped
  fun splashRepository(client: HttpClient, service: DownloadService) = SplashRepository(client, service)

  @Provides
  @ViewModelScoped
  fun downloadService() = DownloadService()

}