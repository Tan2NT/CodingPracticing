package com.tan.hoangngoc.designpattern.factory.juice.store

import com.tan.hoangngoc.designpattern.factory.juice.JuiceType
import com.tan.hoangngoc.designpattern.factory.juice.product.Juice

abstract class JuiceStore {

    fun orderJuice(type: JuiceType): Juice {
        val juice = createJuice(type)

        juice.prepare()
        juice.make()
        juice.box()

        return juice
    }

    protected abstract fun createJuice(type: JuiceType): Juice
}