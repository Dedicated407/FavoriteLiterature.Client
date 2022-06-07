package com.dedicated407.favoriteliteratureclient.Presentation.Views.Books

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import com.dedicated407.favoriteliteratureclient.Presentation.ViewModels.BookInfoViewModel
import com.dedicated407.favoriteliteratureclient.databinding.BookInfoFragmentBinding
import java.lang.Exception

class BookInfoFragment : Fragment() {
    private lateinit var mViewModel: BookInfoViewModel
    private var mBinding: BookInfoFragmentBinding? = null
    private val args: BookInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel = BookInfoViewModel(Repository())
        mBinding = BookInfoFragmentBinding.inflate(layoutInflater, container, false)

        mViewModel.getBook(args.bookId).observe(viewLifecycleOwner) {
            it?.let { book ->
                mBinding!!.bookInfoName.text = book.name
                mBinding!!.bookInfoAuthor.text = book.author.toString()
                mBinding!!.bookInfoRating.text = book.rating.toString()
                mBinding!!.bookInfoDescription.text = book.description

                mBinding?.bookInfoShare?.setOnClickListener {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "https://favlit.ru/book/" + book.id)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
            } ?: activity?.onBackPressed()
        }

        return mBinding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}