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
import com.dedicated407.favoriteliteratureclient.Presentation.ViewModels.AuthorizationViewModel
import com.dedicated407.favoriteliteratureclient.databinding.AuthorizationFragmentBinding

class AuthorizationFragment : Fragment() {
    private lateinit var mViewModel: AuthorizationViewModel
    private lateinit var mBinding: AuthorizationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = AuthorizationFragmentBinding.inflate(layoutInflater, container, false)
        mViewModel = ViewModelProvider(this)[AuthorizationViewModel::class.java]

        if (mViewModel.checkAuthentication(requireContext())) {
            bottomNavShow()
        }

        mBinding.btnAuth.setOnClickListener {
            val email = mBinding.inputAuthLogin.text.toString()
            val password = mBinding.inputAuthPassword.text.toString()

            if (email.isNotEmpty() &&
                password.isNotEmpty()
            ) {
                mViewModel.authenticate(
                    email,
                    password
                )
            } else {
                Toast.makeText(context, "Данные введены не полностью!", Toast.LENGTH_SHORT).show()
            }
        }

        mBinding.btnRegistration.setOnClickListener {
            findNavController().navigate(
                AuthorizationFragmentDirections.actionAuthorizationToRegistration()
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
            AuthorizationFragmentDirections.actionAuthorizationToPersonalAccount()
        )
    }

}