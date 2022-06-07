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

        mBinding.btnAuth.setOnClickListener {
            val email = mBinding.inputAuthLogin.text.toString()
            val password = mBinding.inputAuthPassword.text.toString()
            if (email.isNotEmpty() &&
                password.isNotEmpty()
            ) {
                val response = mViewModel.authentication(
                    email,
                    password
                )
                response.observe(viewLifecycleOwner) {
                    try {
                        requireContext()
                            .getSharedPreferences("AuthKey", Context.MODE_PRIVATE).edit {
                                putString("jwt", it.accessToken)
                            }

                        bottomNavShow()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } else {
                Toast.makeText(context, "One field is empty!",Toast.LENGTH_SHORT).show()
            }
        }

        mViewModel = ViewModelProvider(this)[AuthorizationViewModel::class.java]

        if (mViewModel.checkAuthentication(requireContext())) {
            bottomNavShow()
        }

        return mBinding.root
    }

    private fun bottomNavShow() {
        val bottomNav = (requireActivity() as MainActivity)
            .binding
            .bottomNavigation

        bottomNav.visibility = View.VISIBLE

        findNavController().navigate(
            AuthorizationFragmentDirections.actionAuthToPersonalAccount()
        )
    }

}