package com.trendingkh.androidApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.trendingkh.androidApp.databinding.ActivityMainBinding
import com.trendingkh.androidApp.ui.splash.SplashFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(binding.fragmentContainer.id, SplashFragment())
        .commit()
    }


  }
}
