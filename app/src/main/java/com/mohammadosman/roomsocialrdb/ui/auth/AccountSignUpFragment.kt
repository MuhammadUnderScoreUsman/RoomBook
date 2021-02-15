package com.mohammadosman.roomsocialrdb.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.mohammadosman.roomsocialrdb.R
import com.mohammadosman.roomsocialrdb.databinding.FragmentAccountSignUpBinding
import com.mohammadosman.roomsocialrdb.repository.account.AccountRepositoryImpl.Companion.Success_AccountCreated
import com.mohammadosman.roomsocialrdb.util.Response
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AccountSignUpFragment : BaseAuthFragment() {


    private var _binding: FragmentAccountSignUpBinding? = null
    private val binding: FragmentAccountSignUpBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        binding.btnSignUp.setOnClickListener {
            viewModel.insertUser(
                roomMail = binding.edtTxtEmail.text.toString(),
                userName = binding.edtTxtUserName.text.toString()
            )
        }
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.response.collect {
                when (it) {
                    is Response.Success -> {
                        uiUtils(it.msg)
                        if (it.msg == Success_AccountCreated) {
                            binding.btnSignUp.setBackgroundColor(R.color.design_default_color_primary_dark)
                        }
                    }
                    is Response.Error -> {
                        uiUtils(it.error)
                    }
                    Response.Loading -> {
                    }
                }
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}