package com.flesh.bookshelf.viewmodels

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.switchMap
import com.flesh.bookshelf.objects.Book
import com.flesh.bookshelf.web.BookStoreRepository
import kotlinx.coroutines.Dispatchers

class BookViewModel : ViewModel(){

    private val repository : BookStoreRepository =
        BookStoreRepository()
    private val reloadTrigger = MutableLiveData<Boolean>()
    private val books: LiveData<List<Book>> = switchMap(reloadTrigger){
        liveData(Dispatchers.IO){
            val retrievedBooks = repository.getBooks()
            emit(retrievedBooks)
        }
    }

    init{
        reloadData()
    }

    fun reloadData(){
        reloadTrigger.value = true
    }

    fun getData() = books

}