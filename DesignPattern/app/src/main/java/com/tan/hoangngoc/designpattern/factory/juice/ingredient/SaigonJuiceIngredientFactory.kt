package com.tan.hoangngoc.designpattern.factory.juice.ingredient

class SaigonJuiceIngredientFactory: JuiceIngredientFactory {
    override fun createIngredients(ingredients: ArrayList<String>): ArrayList<String> {
        return arrayListOf("sugar", "honey", "cherry")
    }
}