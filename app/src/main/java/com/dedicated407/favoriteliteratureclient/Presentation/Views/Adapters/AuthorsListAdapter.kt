package com.dedicated407.favoriteliteratureclient.Presentation.Views.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Server.Models.Responses.AuthorsListResponseModel
import com.dedicated407.favoriteliteratureclient.Presentation.Views.Authors.AuthorsListFragmentDirections
import com.dedicated407.favoriteliteratureclient.databinding.ListItemFragmentBinding
import org.jetbrains.annotations.NotNull

class AuthorsListAdapter(private var authors: List<AuthorsListResponseModel>) :
    RecyclerView.Adapter<AuthorsListAdapter.AuthorViewHolder>() {

    @NonNull
    @NotNull
    override fun onCreateViewHolder(
        @NonNull
        @NotNull
        parent: ViewGroup,
        viewType: Int,
    ): AuthorViewHolder {
        val binding: ListItemFragmentBinding =
            ListItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return AuthorViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AuthorViewHolder,
        position: Int,
    ) {
        holder.binding.textViewName.text = authors[position].toString()

        holder.binding.root.setOnClickListener { v ->
            v.findNavController().navigate(
                AuthorsListFragmentDirections.actionListAuthorsToAuthorInfo(authors[position].id.toString())
            )
        }
    }

    override fun getItemCount(): Int {
        return authors.size
    }

    fun getData(): List<AuthorsListResponseModel> {
        return authors
    }

    class AuthorViewHolder(var binding: ListItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root)
}