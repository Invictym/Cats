package com.akalatskij.testtask.model.entity

import io.realm.RealmObject
import io.realm.annotations.Ignore

open class Cat(
    @Ignore
    var breeds: List<Any>,
    @Ignore
    var categories: List<Category>,
    var height: Int,
    var id: String,
    var url: String,
    var width: Int
) : RealmObject() {

    constructor() : this(listOf(), listOf(), 0, "", "", 0)
}

open class Category(
    var id: Int,
    var name: String
)