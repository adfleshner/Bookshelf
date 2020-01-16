package com.flesh.bookshelf.web

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBookClient {
    companion object {

        val bookService by lazy {
            Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(BookService::class.java)
        }
    }
}
