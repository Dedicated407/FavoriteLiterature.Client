package com.dedicated407.favoriteliteratureclient.Presentation.Views.Books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.BooksListResponseModel
import com.dedicated407.favoriteliteratureclient.Presentation.ViewModels.BooksListViewModel
import com.dedicated407.favoriteliteratureclient.Presentation.Views.Adapters.BooksListAdapter
import com.dedicated407.favoriteliteratureclient.databinding.ListFragmentBinding

class BooksListFragment : Fragment() {

    private lateinit var mViewModel: BooksListViewModel
    private lateinit var mBinding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mViewModel = BooksListViewModel(Repository())
        mBinding = ListFragmentBinding.inflate(layoutInflater, container, false)
        mBinding.RecyclerViewList.layoutManager = LinearLayoutManager(context)

        mViewModel.getAllBooks().observe(viewLifecycleOwner) { booksList: List<BooksListResponseModel> ->
            mBinding.RecyclerViewList.adapter = BooksListAdapter(booksList)
        }

        return mBinding.root
    }

}