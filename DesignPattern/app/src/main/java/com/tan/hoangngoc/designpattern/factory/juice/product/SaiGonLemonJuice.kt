package com.tan.hoangngoc.designpattern.factory.juice.product

import com.tan.hoangngoc.designpattern.factory.juice.ingredient.JuiceIngredientFactory

class SaiGonLemonJuice(
    name: String,
    ingredients: ArrayList<String>,
    private val ingredientFactory: JuiceIngredientFactory
): Juice(name, ingredients) {
    override fun prepare() {
        ingredientFactory.createIngredients(ingredients)
    }
}