package ru.kartyshova.bookinfo.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val API_KEY = "AIzaSyDer6sKnj5AR8nUCqGC90mmgs6zj5qVTOc"

interface BooksApi{

    @GET("volumes?key=$API_KEY")
    suspend fun getAllBooks (@Query("q") query: String): BooksResponse

    @GET("volumes/{bookId}?key=$API_KEY")
    suspend fun getBookInfo(@Path("bookId") bookId: String): Book
}