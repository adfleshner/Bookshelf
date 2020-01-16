package com.flesh.bookshelf.contract

import androidx.lifecycle.LifecycleOwner
import com.flesh.bookshelf.viewmodels.BookViewModel
import com.flesh.bookshelf.objects.Book

interface BookStoreContract {
    interface View {
        fun showBooks(books: List<Book>) // 1
        fun showLoader() // 2
        fun hideLoader() // 3
        fun showEmpty()
        fun showError()
    }

    interface Presenter {
        fun attach(view: View, lco: LifecycleOwner, bookViewModel: BookViewModel) // 4
        fun loadBooks() // 5
    }
}