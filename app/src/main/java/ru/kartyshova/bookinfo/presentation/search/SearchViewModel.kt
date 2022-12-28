package ru.kartyshova.bookinfo.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kartyshova.bookinfo.data.Book
import ru.kartyshova.bookinfo.data.BooksResponse
import ru.kartyshova.bookinfo.domain.GetBooksUseCase

class SearchViewModel(
    private val getBooksUseCase: GetBooksUseCase
): ViewModel() {
    private val _items = MutableLiveData<BooksResponse>()
    val items: LiveData<BooksResponse> = _items

    fun search(string: String) {
        viewModelScope.launch {
            getBooksUseCase(string)
                .onSuccess { _items.value = it }
                .onFailure { Exception("e")}
        }
    }
}