package com.mohammadosman.roomsocialrdb.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohammadosman.roomsocialrdb.R
import com.mohammadosman.roomsocialrdb.databinding.FragmentPostBinding
import com.mohammadosman.roomsocialrdb.ui.auth.SignInFragment
import kotlinx.coroutines.launch

class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding: FragmentPostBinding get() = _binding!!

    private val viewModel by viewModels<PostViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        binding.btnLogout.setOnClickListener {
            lifecycleScope.launch {
                if (viewModel.logout()) {
                    parentFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<SignInFragment>(R.id.fragment_container_view)
                    }
                } else {
                    return@launch
                }
            }

        }
    }

    private fun initObserver() {

    }


    private fun initAdapter() {
        binding.resViewPost.apply {
            layoutManager = LinearLayoutManager(requireContext())

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}