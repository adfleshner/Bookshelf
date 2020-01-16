package com.flesh.bookshelf.web

class BookStoreRepository{

    var client : BookService = RetrofitBookClient.bookService
    suspend fun getBooks() = client.getBooks().shuffled()

}