package com.dedicated407.favoriteliteratureclient.Presentation.Views.Books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.BookRepository
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
        mViewModel = BooksListViewModel(BookRepository())
        mBinding = ListFragmentBinding.inflate(layoutInflater, container, false)
        mBinding.RecyclerViewList.layoutManager = LinearLayoutManager(context)

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) { }
        }).attachToRecyclerView(mBinding.RecyclerViewList)

        mViewModel.getAllBooks().observe(viewLifecycleOwner) { booksList: List<BooksListResponseModel> ->
            mBinding.RecyclerViewList.adapter = BooksListAdapter(booksList)
        }

        return mBinding.root
    }

}