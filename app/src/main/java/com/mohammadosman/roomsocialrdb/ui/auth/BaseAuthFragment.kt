package com.mohammadosman.roomsocialrdb.ui.auth

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.mohammadosman.roomsocialrdb.R


abstract class BaseAuthFragment : Fragment() {

    protected val viewModel by activityViewModels<AccountViewModel>()

    protected fun uiUtils(msg: String?) {
        if (msg?.contains("!") == true) {
            MaterialDialog(requireContext()).show {
                positiveButton(R.string.positive_btn)
                message(text = msg)
                cancelable(true)
            }
        } else {
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }
    }




}