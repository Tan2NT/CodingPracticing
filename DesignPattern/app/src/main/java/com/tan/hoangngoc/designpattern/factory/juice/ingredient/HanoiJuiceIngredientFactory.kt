package com.tan.hoangngoc.designpattern.factory.juice.ingredient

import com.tan.hoangngoc.designpattern.factory.juice.product.Juice

class HanoiJuiceIngredientFactory: JuiceIngredientFactory {
    override fun createIngredients(ingredients: ArrayList<String>): ArrayList<String> {
        return arrayListOf("sugar", "salt", "ginger", "almond", "strawberry")
    }
}