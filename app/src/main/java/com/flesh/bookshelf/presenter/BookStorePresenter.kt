package com.flesh.bookshelf.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.flesh.bookshelf.viewmodels.BookViewModel
import com.flesh.bookshelf.contract.BookStoreContract


// 1
class BookStorePresenter : BookStoreContract.Presenter {
    // 2
    private lateinit var view: BookStoreContract.View
    private lateinit var viewModel: BookViewModel
    // 3
    override fun attach(
        view: BookStoreContract.View,
        lco: LifecycleOwner,
        bookViewModel: BookViewModel
    ) {
        this.view = view
        this.viewModel = bookViewModel
        bookViewModel.getData().observe(lco, Observer {
            view.hideLoader()
            if (it.isNotEmpty()) {
                view.showBooks(it)
            } else {
                view.showEmpty()
            }
        })
    }

    // 4
    override fun loadBooks() {
        view.showLoader()
        viewModel.getData()
    }

    fun reloadBooks() {
        viewModel.reloadData()
    }
}