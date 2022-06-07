package com.dedicated407.favoriteliteratureclient.Presentation.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dedicated407.favoriteliteratureclient.MainActivity
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import com.dedicated407.favoriteliteratureclient.Presentation.ViewModels.PersonalAccountViewModel
import com.dedicated407.favoriteliteratureclient.databinding.PersonalAccountFragmentBinding

class PersonalAccountFragment : Fragment() {
    private lateinit var mBinding: PersonalAccountFragmentBinding
    private lateinit var mViewModel: PersonalAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("AuthKey", Context.MODE_PRIVATE)
        if (sharedPreferences.getString("email", null) == null)
            findNavController().navigate(
                PersonalAccountFragmentDirections.actionPersonalAccountToAuth()
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel = PersonalAccountViewModel(Repository())
        mBinding = PersonalAccountFragmentBinding.inflate(layoutInflater, container, false)

        mViewModel.getUserInfo().observe(viewLifecycleOwner) {
            it?.let { user ->
                mBinding.personalAccountUserName.setText(user.userName)
                mBinding.personalAccountEmail.text = user.email
                mBinding.personalAccountFirstName.setText(user.firstName)
                mBinding.personalAccountLastName.setText(user.lastName)
                mBinding.personalAccountPatronymic.setText(user.patronymic)
                mBinding.personalAccountRole.text = user.role
            }
        }

        mBinding.btnLogOut.setOnClickListener {
            val isSignOut = mViewModel.signOut(requireContext())

            if (isSignOut) {
                val bottomNav = (requireActivity() as MainActivity)
                    .binding
                    .bottomNavigation

                bottomNav.visibility = View.GONE

                findNavController().navigate(
                    PersonalAccountFragmentDirections.actionPersonalAccountToAuth()
                )
            } else {
                Toast.makeText(context, "You can`t sign out", Toast.LENGTH_SHORT).show()
            }
        }

        return mBinding.root
    }

}