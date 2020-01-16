package com.flesh.bookshelf.web

import com.flesh.bookshelf.objects.Book
import retrofit2.http.GET

interface BookService {

    @GET("tarek360/4578e33621011e18829bad0c8d1c8cdf/raw/06d185bebc3e14a56dfa85f53288daddd4ff6a2b/books.json")
    suspend fun getBooks(): List<Book>

}
