package com.example.trylistjson
//класс даных с полями название жанр и автор
data class book(
    val title:String,
    val genre :String,
    val author:String,
    val year_of_publishing :String,
    val cover_type :String,
    val number_of_pages:String


    ) {
}