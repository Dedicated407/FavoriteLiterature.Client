package com.dedicated407.favoriteliteratureclient.Presentation.Views.Authors

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dedicated407.favoriteliteratureclient.Presentation.Repository.Repository
import com.dedicated407.favoriteliteratureclient.Presentation.ViewModels.AuthorInfoViewModel
import com.dedicated407.favoriteliteratureclient.databinding.AuthorInfoFragmentBinding

class AuthorInfoFragment : Fragment() {
    private lateinit var mViewModel: AuthorInfoViewModel
    private lateinit var mBinding: AuthorInfoFragmentBinding
    private val args: AuthorInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = AuthorInfoFragmentBinding.inflate(layoutInflater, container, false)
        mViewModel = AuthorInfoViewModel(Repository())

        mViewModel.getAuthor(args.authorId).observe(viewLifecycleOwner) {
            it?.let { author ->
                mBinding.authorInfoName.text = author.toString()
                mBinding.authorInfoNickName.text = author.userName
                mBinding.authorInfoRating.text = author.rating.toString()
                mBinding.authorInfoBooksList.text = author.bookNames?.joinToString(separator = "; ")
                mBinding.authorInfoDescription.text = author.description

                mBinding.authorInfoShare.setOnClickListener {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "https://favlit.ru/author/" + author.id)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
            } ?: activity?.onBackPressed()
        }

        return mBinding.root
    }
}