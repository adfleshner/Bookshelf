/*
 * ------------------------------------------------------------
 * "THE BEERWARE LICENSE" (Revision 42):
 * adfleshner wrote this code. As long as you retain this
 * notice, you can do whatever you want with this stuff. If we
 * meet someday, and you think this stuff is worth it, you can
 * buy me a beer in return.
 * ------------------------------------------------------------
 */

package com.flesh.bookshelf.web

class BookStoreRepository{

    var client : BookService = RetrofitBookClient.bookService
    suspend fun getBooks() = client.getBooks().shuffled()

}