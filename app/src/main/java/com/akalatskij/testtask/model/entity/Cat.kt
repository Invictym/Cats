package com.akalatskij.testtask.model.entity

import io.realm.RealmObject

open class Cat(
    var id: String = "",
    var image: ByteArray,
    var save: Boolean = true
) : RealmObject() {
    constructor(): this("", ByteArray(0))
}
