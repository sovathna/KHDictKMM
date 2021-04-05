package com.trendingkh.androidApp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.trendingkh.androidApp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class SplashFragment : Fragment() {

  private val viewModel: SplashViewModel by viewModels()
  private var _binding: FragmentSplashBinding? = null
  private val binding: FragmentSplashBinding get() = _binding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.downloadDatabase(requireContext())
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentSplashBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.state.observe(viewLifecycleOwner) {
      with(binding) {
        progressBar.isIndeterminate = it.isLoading
        progressBar.max = it.total.roundToInt()
        progressBar.setProgress(it.processed.roundToInt(), true)
        tvStatus.text = String.format("%.01fMB/%.01fMB", it.processed, it.total)
        if(it.isSuccess){
          tvTitle.text = "Downloaded Successfully"
          tvStatus.visibility = View.INVISIBLE
          progressBar.visibility = View.INVISIBLE
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }


}