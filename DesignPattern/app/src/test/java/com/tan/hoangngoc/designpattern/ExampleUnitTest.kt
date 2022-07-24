package com.tan.hoangngoc.designpattern

import com.tan.hoangngoc.designpattern.factory.juice.JuiceType
import com.tan.hoangngoc.designpattern.factory.juice.product.HanoiLemonJuice
import com.tan.hoangngoc.designpattern.factory.juice.store.HanoiStyleJuiceStore
import com.tan.hoangngoc.designpattern.factory.juice.store.JuiceStore
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun create_hanoi_lemon_juice_should_return_correct_juice() {
        val store: JuiceStore = HanoiStyleJuiceStore()
        val juice = store.orderJuice(JuiceType.LEMON)
        assert(juice is HanoiLemonJuice)
    }
}