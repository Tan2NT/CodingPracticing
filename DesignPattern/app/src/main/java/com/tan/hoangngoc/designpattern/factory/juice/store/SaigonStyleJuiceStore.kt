package com.tan.hoangngoc.designpattern.factory.juice.store

import com.tan.hoangngoc.designpattern.factory.juice.JuiceType
import com.tan.hoangngoc.designpattern.factory.juice.product.Juice
import com.tan.hoangngoc.designpattern.factory.juice.product.SaiGonLemonJuice
import com.tan.hoangngoc.designpattern.factory.juice.product.SaigonAvocadoSmoothie

class SaigonStyleJuiceStore: JuiceStore() {
    override fun createJuice(type: JuiceType): Juice {
        return when (type) {
            JuiceType.LEMON -> SaiGonLemonJuice(
                name = "SWEET-FRESH LEMON JUICE",
                ingredients = arrayListOf("sugar", "honey")
            )

            JuiceType.AVOCADO -> SaigonAvocadoSmoothie(
                name = "SWEET AVOCADO SMOOTHIE",
                gradients = arrayListOf("sugar", "cherry")
            )
        }
    }
}