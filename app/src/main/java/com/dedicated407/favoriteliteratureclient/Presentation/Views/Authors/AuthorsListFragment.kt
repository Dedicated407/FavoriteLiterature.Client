package com.dedicated407.favoriteliteratureclient.Presentation.Views.Authors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.AuthorsListResponseModel
import com.dedicated407.favoriteliteratureclient.Presentation.ViewModels.AuthorsListViewModel
import com.dedicated407.favoriteliteratureclient.Presentation.Views.Adapters.AuthorsListAdapter
import com.dedicated407.favoriteliteratureclient.databinding.ListAuthorsItemFragmentBinding

class AuthorsListFragment : Fragment() {
    private lateinit var mViewModel: AuthorsListViewModel
    private lateinit var mBinding: ListAuthorsItemFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = ListAuthorsItemFragmentBinding.inflate(layoutInflater, container, false)
        mBinding.AuthorsList.RecyclerViewList.layoutManager = LinearLayoutManager(context)

        mBinding.findAuthorSearchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    mViewModel.setAuthorsList(query)
                    return true
                }
            }
        )

        mViewModel = AuthorsListViewModel(Repository())
        mViewModel.getAllAuthors().observe(viewLifecycleOwner) { authorsList: List<AuthorsListResponseModel> ->
            mBinding.AuthorsList.RecyclerViewList.adapter = AuthorsListAdapter(authorsList)
        }

        return mBinding.root
    }

}