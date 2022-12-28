package ru.kartyshova.bookinfo.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kartyshova.bookinfo.data.BooksApi
import ru.kartyshova.bookinfo.presentation.search.SearchViewModel
import ru.kartyshova.bookinfo.data.BooksRepository
import ru.kartyshova.bookinfo.data.BooksRepositoryImpl
import ru.kartyshova.bookinfo.domain.GetBooksUseCase
import ru.kartyshova.bookinfo.domain.GetItemUseCase

val dependency = module {
    single<BooksApi> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@single retrofit.create(BooksApi::class.java)
    }

    single<BooksRepository> { BooksRepositoryImpl(get()) }

    single { GetBooksUseCase(get()) }
    single { GetItemUseCase(get()) }

    viewModel { SearchViewModel(get()) }

}