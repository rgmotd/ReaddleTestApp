package com.example.readdletestapp

import java.io.Serializable
import java.util.*

data class Item(
    val id: Long,
    var text: String,
    val imageUrl: String,
    val status: Boolean,
) : Serializable {
    val email = "${text.toLowerCase(Locale.ROOT).replace(" ", ".")}@gmail.com"
}
