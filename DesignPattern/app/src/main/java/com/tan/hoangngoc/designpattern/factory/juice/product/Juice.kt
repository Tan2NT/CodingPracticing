package com.tan.hoangngoc.designpattern.factory.juice.product

open abstract class Juice (
    private val name: String,
    protected val ingredients: ArrayList<String>
) {

    abstract fun prepare()

    fun make() { println("----- making the juice -----") }

    fun box() { println("----- boxing the juice -----") }
}