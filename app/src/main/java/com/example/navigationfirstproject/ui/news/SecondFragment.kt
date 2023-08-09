package com.example.navigationfirstproject.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.navigationfirstproject.R
import com.example.navigationfirstproject.RoomDatabase
import com.example.navigationfirstproject.databinding.FragmentSecondBinding
import kotlinx.coroutines.launch


class SecondFragment : Fragment(R.layout.fragment_second) {

    lateinit var binding: FragmentSecondBinding
    private val viewModel: SecondFragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)

        observeNewsListState()
        viewModel.getNews(RoomDatabase.getDatabase(requireContext()))
    }
    private fun observeNewsListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.newsListState.collect {
                    when (it) {
                        is NewsListState.Idle -> {}
                        is NewsListState.Loading -> {}
                        is NewsListState.Result -> {
                            binding.rvNews.adapter =NewsAdapter(requireContext(),it.news){
                                val bundle =Bundle()
                                bundle.putInt("id",id)
                                findNavController().navigate(R.id.action_secondFragment_to_thirdFragment,bundle)
                            }
                        }
                        is NewsListState.Empty -> {}
                        is NewsListState.Error -> {}
                    }
                }
            }
        }
    }
}