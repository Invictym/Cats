package com.akalatskij.testtask.model.entity

import io.realm.RealmObject

open class Cat(
    var id: String = "",
    var url: String = "",
    var save: Boolean = true
) : RealmObject() {

    constructor() : this("")
}
