package com.tan.hoangngoc.designpattern.factory.juice.store

import com.tan.hoangngoc.designpattern.factory.juice.JuiceType
import com.tan.hoangngoc.designpattern.factory.juice.product.HanoiAvocadoSmoothie
import com.tan.hoangngoc.designpattern.factory.juice.product.HanoiLemonJuice
import com.tan.hoangngoc.designpattern.factory.juice.product.Juice

class HanoiStyleJuiceStore : JuiceStore() {
    override fun createJuice(type: JuiceType): Juice {
        return when (type) {
            JuiceType.LEMON -> HanoiLemonJuice(
                name = "COOL - HEALTHY LEMON JUICE",
                gradients = arrayListOf("sugar", "ginger", "salt", "almond")
            )

            JuiceType.AVOCADO -> HanoiAvocadoSmoothie(
                name = "LOVELY AVOCADO SMOOTHIE",
                gradients = arrayListOf("mango", "strawberry")
            )
        }
    }
}