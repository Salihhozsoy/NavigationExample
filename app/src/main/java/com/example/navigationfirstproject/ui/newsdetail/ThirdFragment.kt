package com.example.navigationfirstproject.ui.newsdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.navigationfirstproject.R
import com.example.navigationfirstproject.RoomDatabase
import com.example.navigationfirstproject.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    lateinit var binding: FragmentThirdBinding
    private val viewModel:ThirdFragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentThirdBinding.bind(view)

        arguments?.getInt("id")?.let {
            viewModel.getNewsById(it,RoomDatabase.getDatabase(requireContext()))
        }

    }

}
