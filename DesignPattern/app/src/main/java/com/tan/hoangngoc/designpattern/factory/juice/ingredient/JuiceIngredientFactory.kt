package com.tan.hoangngoc.designpattern.factory.juice.ingredient

interface JuiceIngredientFactory {
    fun createIngredients(ingredients: ArrayList<String>): ArrayList<String>
}