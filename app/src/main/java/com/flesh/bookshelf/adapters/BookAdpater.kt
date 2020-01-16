package com.flesh.bookshelf.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.flesh.bookshelf.R
import com.flesh.bookshelf.objects.Book
import kotlinx.android.synthetic.main.grid_item_book.view.*

class BookAdpater : RecyclerView.Adapter<BookAdpater.BookViewHolder>() {

   private val list = mutableListOf<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.grid_item_book,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    fun setData(data : List<Book>){
        list.clear()
        notifyDataSetChanged()
        data.forEach {
            addItem(it)
        }
    }

    fun addItem(book: Book) {
        list.add(book)
        notifyItemInserted(list.size)
    }

    fun removeItem(book: Book) {
        val position = list.indexOf(book)
        if (position != -1) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun updateItem(book: Book) {
        val position = list.indexOf(book)
        if (position != -1) {
            list[position] = book
            notifyItemChanged(position)
        }
    }

    class BookViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bindView(book: Book) {
            with(itemView){
                with(book){
                    tvBooktTitle.text = title
                    tvBookDesc.text = description
                    tvBookPrice.text = price
                    ivBookCover.load(coverUrl)
                    btnBookDetails.setOnClickListener {
                        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
                        val customTabsIntent: CustomTabsIntent = builder.build()
                        customTabsIntent.launchUrl(this@BookViewHolder.itemView.context, Uri.parse(url))
                    }
                }

            }
        }
    }
}