package ru.kartyshova.bookinfo.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.kartyshova.bookinfo.databinding.BookItemBinding
import ru.kartyshova.bookinfo.data.Book
import ru.kartyshova.bookinfo.data.BooksResponse

class BooksAdapter : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    var book = listOf<Book>()
    var onClick: (Book) -> Unit = {}

    class BooksViewHolder(viewBinding: BookItemBinding) : ViewHolder(viewBinding.root) {
        val title = viewBinding.itemTitle
        val subtitle = viewBinding.itemSubtitle
        val authors = viewBinding.itemAuthors
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val viewBinding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val bookInfo = book[position]
        val volumeInfo = bookInfo.volumeInfo

        holder.title.text = volumeInfo.title

        holder.subtitle.text = volumeInfo.subtitle
        holder.subtitle.isGone = volumeInfo.subtitle.isNullOrBlank()

        holder.authors.text = volumeInfo.authors?.joinToString() ?: ""
        holder.authors.isGone = volumeInfo.authors.isNullOrEmpty()

        holder.itemView.setOnClickListener { onClick(bookInfo) }
    }

    override fun getItemCount(): Int = book.size

}