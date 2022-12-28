package ru.kartyshova.bookinfo.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.kartyshova.bookinfo.data.Book
import ru.kartyshova.bookinfo.databinding.BookInfoBinding

class BookInfoFragment: Fragment (){

    private var bookInfoBinding: BookInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookInfoBinding = BookInfoBinding.inflate(inflater, container, false)
        return bookInfoBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book = arguments?.getParcelable<Book>("arg") ?: return
        bookInfoBinding?.run {
            title.text = book.volumeInfo.title

            authors.text = book.volumeInfo.authors?.joinToString()
            notes.text = book.volumeInfo.description
            publishedDate.text = book.volumeInfo.publishedDate
            previewLink.text = book.volumeInfo.previewLink
            previewLink.setOnClickListener {
                val openUrl = Intent(Intent.ACTION_VIEW)
                openUrl.data = Uri.parse(book.volumeInfo.previewLink)
                startActivity(openUrl)

                Glide.with(requireContext()).load(book.volumeInfo.imageLinks?.smallThumbnail)
                    .into(imageLinks)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bookInfoBinding = null
    }
}