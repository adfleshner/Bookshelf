/*
 * ------------------------------------------------------------
 * "THE BEERWARE LICENSE" (Revision 42):
 * adfleshner wrote this code. As long as you retain this
 * notice, you can do whatever you want with this stuff. If we
 * meet someday, and you think this stuff is worth it, you can
 * buy me a beer in return.
 * ------------------------------------------------------------
 */

package com.flesh.bookshelf.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.flesh.bookshelf.viewmodels.BookViewModel
import com.flesh.bookshelf.contract.BookStoreContract


class BookStorePresenter : BookStoreContract.Presenter {

    private lateinit var view: BookStoreContract.View
    private lateinit var viewModel: BookViewModel

    override fun attach(
        view: BookStoreContract.View, lco: LifecycleOwner,
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

    override fun loadBooks() {
        view.showLoader()
        viewModel.getData()
    }

    fun reloadBooks() {
        viewModel.reloadData()
    }
}