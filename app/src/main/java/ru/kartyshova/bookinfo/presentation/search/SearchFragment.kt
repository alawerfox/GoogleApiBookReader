package ru.kartyshova.bookinfo.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kartyshova.bookinfo.databinding.SearchBinding

class SearchFragment : Fragment() {

    private var searchBinding: SearchBinding? = null
    private val viewModel: SearchViewModel by viewModel()
    private val navController: NavController by lazy { findNavController() }
    private val booksAdapter = BooksAdapter().apply {
        onClick = {
            navController.navigate(SearchFragmentDirections.openBookInfoFragment(it))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.items.observe(this) {
            booksAdapter.book = it.items
            booksAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchBinding = SearchBinding.inflate(inflater, container, false)
        return searchBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBinding?.searchFiled?.doOnTextChanged { text, _, _, _ ->
            viewModel.search(text.toString())
        }

        searchBinding?.searchItem?.adapter = booksAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        searchBinding = null
    }
}




