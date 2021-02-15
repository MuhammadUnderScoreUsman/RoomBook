package com.mohammadosman.roomsocialrdb.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.afollestad.materialdialogs.MaterialDialog
import com.mohammadosman.roomsocialrdb.R
import com.mohammadosman.roomsocialrdb.ui.auth.AccountViewModel
import com.mohammadosman.roomsocialrdb.ui.auth.SignInFragment
import com.mohammadosman.roomsocialrdb.ui.posts.PostFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<AccountViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {

            lifecycleScope.launch {
                if(viewModel.checkUserAuth()){
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<PostFragment>(R.id.fragment_container_view)
                    }
                }else {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<SignInFragment>(R.id.fragment_container_view)
                    }
                }
            }

        }
    }
}