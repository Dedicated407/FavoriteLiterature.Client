package com.dedicated407.favoriteliteratureclient.Presentation.Views.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.BooksListResponseModel
import com.dedicated407.favoriteliteratureclient.Presentation.Views.Books.BooksListFragmentDirections
import com.dedicated407.favoriteliteratureclient.databinding.ListBooksItemFragmentBinding
import org.jetbrains.annotations.NotNull

class BooksListAdapter(private var books: List<BooksListResponseModel>) :
    RecyclerView.Adapter<BooksListAdapter.BookViewHolder>() {

    @NonNull
    @NotNull
    override fun onCreateViewHolder(
        @NonNull
        @NotNull
        parent: ViewGroup,
        viewType: Int,
    ): BookViewHolder {
        val binding: ListBooksItemFragmentBinding =
            ListBooksItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int,
    ) {
        val book = books[position]
        holder.binding.itemBookName.text = book.name
        holder.binding.itemDescriptionName.text = book.description

        holder.binding.cardViewList.setOnClickListener { v ->
            v.findNavController().navigate(
                BooksListFragmentDirections.actionListBooksToBookInfo(books[position].id.toString())
            )
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun getData(): List<BooksListResponseModel> {
        return books
    }

    class BookViewHolder(var binding: ListBooksItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root)
}