package com.imbana5.unofficialkecipir

import com.imbana5.unofficialkecipir.repository.Repository
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun getBanners() {
        val banners = Repository.getBanners().value
        println("banners: " + banners);
    }
}