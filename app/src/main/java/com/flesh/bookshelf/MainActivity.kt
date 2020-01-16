package com.flesh.bookshelf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.flesh.bookshelf.adapters.BookAdpater
import com.flesh.bookshelf.contract.BookStoreContract
import com.flesh.bookshelf.objects.Book
import com.flesh.bookshelf.presenter.BookStorePresenter
import com.flesh.bookshelf.viewmodels.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookStoreContract.View {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var adpater: BookAdpater
    private var bookPresenter =
        BookStorePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
        initViewModelAndPresenter()
    }

    private fun initList() {
        adpater = BookAdpater()
        listBooks.adapter = adpater
        listBooks.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.spancount))
        srlBooks.setOnRefreshListener { bookPresenter.reloadBooks() }
    }

    private fun initViewModelAndPresenter() {
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        bookPresenter.attach(this, this, bookViewModel)
        bookPresenter.loadBooks()
    }


    override fun showBooks(books: List<Book>) {
        listBooks.visibility = VISIBLE
        adpater.setData(books)
    }

    override fun showLoader() {
        srlBooks.isRefreshing = true
        tvBookesEmptyView.visibility = GONE
        listBooks.visibility = GONE
    }

    override fun hideLoader() {
        srlBooks.isRefreshing = false
    }

    override fun showEmpty() {
        tvBookesEmptyView.visibility = VISIBLE
    }

    override fun showError() {
        tvBookesEmptyView.visibility = VISIBLE
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }
}
