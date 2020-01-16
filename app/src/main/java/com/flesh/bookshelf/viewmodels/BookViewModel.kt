/*
 * ------------------------------------------------------------
 * "THE BEERWARE LICENSE" (Revision 42):
 * adfleshner wrote this code. As long as you retain this
 * notice, you can do whatever you want with this stuff. If we
 * meet someday, and you think this stuff is worth it, you can
 * buy me a beer in return.
 * ------------------------------------------------------------
 */
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