package com.trendingkh.androidApp.ui.splash

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trendingkh.shared.SplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repo: SplashRepository) : ViewModel() {

  private val _state = MutableLiveData(SplashState())
  val state: LiveData<SplashState> get() = _state

  fun downloadDatabase(context: Context) {
    _state.postValue(_state.value?.copy(isLoading = true))
    viewModelScope.launch(Dispatchers.IO) {
      val isSuccess =
        repo.downloadDatabase(context.getDatabasePath("kh.db")) { a, b ->
          val processed: Float = a / (1024 * 1024F)
          val total: Float = b / (1024 * 1024F)
          _state.postValue(_state.value?.copy(isLoading = false, processed = processed, total = total))
        }
      _state.postValue(_state.value?.copy(isSuccess = isSuccess))
    }
  }

}

data class SplashState(
  val isInit: Boolean = true,
  val isLoading: Boolean = false,
  val processed: Float = 0F,
  val total: Float = 0F,
  val isSuccess: Boolean = false
)