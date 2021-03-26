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
import com.mohammadosman.roomsocialrdb.ui.TopItemSpacing
import com.mohammadosman.roomsocialrdb.ui.auth.SignInFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding: FragmentPostBinding get() = _binding!!

    private val viewModel by viewModels<PostViewModel>()
    lateinit var postAdapter: PostAdapter


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
        initObserver()
        logout()
        createBlog()
    }

    private fun initObserver() {


        lifecycleScope.launch {
            viewModel.getAllPost().collect {
                postAdapter.submitList(it.reversed())
                postAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initAdapter() {
        binding.resViewPost.apply {
            layoutManager = LinearLayoutManager(requireContext())
            postAdapter = PostAdapter()
            addItemDecoration(TopItemSpacing(30))
            adapter = postAdapter
        }
    }

    private fun createBlog() {

        binding.createPost.setOnClickListener {
            viewModel.createPost(
                R.drawable.cake,
                postDesc = "Hmmm222!! this cake looks so delicious."
            )
            binding.resViewPost.smoothScrollToPosition(0)
        }
    }

    private fun logout() {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}