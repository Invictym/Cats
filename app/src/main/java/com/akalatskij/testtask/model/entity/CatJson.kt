package com.akalatskij.testtask.model.entity

data class CatJson (
    var breeds: List<Any> = listOf(),
    var categories: List<Category> = listOf(),
    var height: Int = 0,
    var id: String = "",
    var url: String = "",
    var width: Int = 0
)

open class Category(
    var id: Int,
    var name: String
)