package com.tan.hoangngoc.designpattern.factory.juice.product

open class Juice (
    private val name: String,
    private val gradients: ArrayList<String>
) {

    fun prepare() { println("----- prepare for $name -----") }

    fun make() { println("----- making the juice -----") }

    fun box() { println("----- boxing the juice -----") }
}