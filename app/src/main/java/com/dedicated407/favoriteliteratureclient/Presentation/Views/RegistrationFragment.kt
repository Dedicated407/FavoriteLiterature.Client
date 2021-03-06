package com.dedicated407.favoriteliteratureclient.Presentation.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dedicated407.favoriteliteratureclient.MainActivity
import com.dedicated407.favoriteliteratureclient.Presentation.ViewModels.RegistrationViewModel
import com.dedicated407.favoriteliteratureclient.databinding.RegistrationFragmentBinding

class RegistrationFragment : Fragment() {
    private lateinit var mViewModel: RegistrationViewModel
    private lateinit var mBinding: RegistrationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = RegistrationFragmentBinding.inflate(layoutInflater, container, false)
        mViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]

        mBinding.btnRegistration.setOnClickListener {
            val userName = mBinding.inputRegistrationUserName.text.toString()
            val email = mBinding.inputRegistrationEmail.text.toString()
            val password = mBinding.inputRegistrationPassword.text.toString()
            val passwordConfirmation = mBinding.inputRegistrationPasswordConfirmation.text.toString()

            if (userName.isNotEmpty() &&
                email.isNotEmpty() &&
                password.isNotEmpty() &&
                passwordConfirmation.isNotEmpty()
            ) {
                mViewModel.register(
                    userName,
                    email,
                    password,
                    passwordConfirmation
                )
            } else {
                Toast.makeText(context, "Данные введены не полностью!", Toast.LENGTH_SHORT).show()
            }
        }

        mBinding.btnBack.setOnClickListener {
            findNavController().navigate(
                RegistrationFragmentDirections.actionRegistrationToAuthorization()
            )
        }

        mViewModel.response.observe(viewLifecycleOwner) {
            it?.let { token ->
                requireContext()
                    .getSharedPreferences("AuthKey", Context.MODE_PRIVATE).edit {
                        putString("jwt", token.accessToken)
                    }

                bottomNavShow()
            }
        }

        mViewModel.lastError.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        return mBinding.root
    }

    private fun bottomNavShow() {
        val bottomNav = (requireActivity() as MainActivity)
            .binding
            .bottomNavigation

        bottomNav.visibility = View.VISIBLE

        findNavController().navigate(
            RegistrationFragmentDirections.actionRegistrationToPersonalAccount()
        )
    }
}