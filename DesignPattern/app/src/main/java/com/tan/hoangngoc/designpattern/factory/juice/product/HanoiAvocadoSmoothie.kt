package com.tan.hoangngoc.designpattern.factory.juice.product

import com.tan.hoangngoc.designpattern.factory.juice.ingredient.JuiceIngredientFactory

class HanoiAvocadoSmoothie(
    name: String,
    gradients: ArrayList<String>,
    private val ingredientFactory: JuiceIngredientFactory
): Juice(name, gradients) {
    override fun prepare() {
        ingredientFactory.createIngredients(ingredients)
    }
}