package ru.kartyshova.bookinfo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kartyshova.bookinfo.data.Book
import ru.kartyshova.bookinfo.data.BooksRepository
import ru.kartyshova.bookinfo.data.BooksResponse

class GetBooksUseCase(
    private val booksRepository: BooksRepository
) {

    suspend operator fun invoke(search: String): Result<BooksResponse> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            booksRepository.getAllBooks(search)
        }

    }
}

