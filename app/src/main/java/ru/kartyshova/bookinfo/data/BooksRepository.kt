package ru.kartyshova.bookinfo.data

interface BooksRepository {
    suspend fun getAllBooks(search: String):BooksResponse
    suspend fun getBookInfo(bookId:String): Book
}

class BooksRepositoryImpl(
    private val booksApi: BooksApi
) : BooksRepository {

    override suspend fun getAllBooks(search: String): BooksResponse {
        return booksApi.getAllBooks(search)

    }

    override suspend fun getBookInfo(bookId: String): Book {
        return booksApi.getBookInfo(bookId)
    }
}




