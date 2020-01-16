/*
 * ------------------------------------------------------------
 * "THE BEERWARE LICENSE" (Revision 42):
 * adfleshner wrote this code. As long as you retain this
 * notice, you can do whatever you want with this stuff. If we
 * meet someday, and you think this stuff is worth it, you can
 * buy me a beer in return.
 * ------------------------------------------------------------
 */
package com.flesh.bookshelf.objects

data class Book(val title: String,
                val price: String,
                val description: String,
                val url: String,
                val coverUrl: String)