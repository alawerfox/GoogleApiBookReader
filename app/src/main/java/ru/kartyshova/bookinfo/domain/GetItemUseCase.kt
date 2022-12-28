package ru.kartyshova.bookinfo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kartyshova.bookinfo.data.Book
import ru.kartyshova.bookinfo.data.BooksApi

class GetItemUseCase(private val booksApi: BooksApi){

    suspend operator fun invoke(bookId: String): Result<Book> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
             booksApi.getBookInfo(bookId)
        }
    }

}