package com.mohammadosman.roomsocialrdb.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.mohammadosman.roomsocialrdb.R
import com.mohammadosman.roomsocialrdb.databinding.FragmentSignInBinding
import com.mohammadosman.roomsocialrdb.repository.account.AccountRepositoryImpl.Companion.SuccessFully_LoginIn
import com.mohammadosman.roomsocialrdb.ui.posts.PostFragment
import com.mohammadosman.roomsocialrdb.util.Response
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SignInFragment : BaseAuthFragment() {


    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signIn()
        createAcc()
        iniObserver()
    }

    private fun signIn() {
        binding.btnSignIn.setOnClickListener {
            viewModel.loginAccount(
                binding.edtTxtUserNameSignInField.text.toString()
            )
        }
    }

    private fun createAcc() {
        binding.txtViewNoAccount.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<AccountSignUpFragment>(R.id.fragment_container_view)
                addToBackStack("SignInFragment")
            }
        }
    }

    private fun iniObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.response.collect {
                when (it) {
                    is Response.Success -> {
                       uiUtils(it.msg)
                        if(it.msg == SuccessFully_LoginIn){
                            parentFragmentManager.commit {
                                setReorderingAllowed(true)
                                replace<PostFragment>(R.id.fragment_container_view)
                                addToBackStack("SignInFragment")
                            }
                        }
                    }
                    is Response.Error -> {
                        uiUtils(it.error)
                    }
                    Response.Loading -> {}
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}