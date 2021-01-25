package com.tim.burtons.utils

import com.tim.burtons.model.Product

object Utils {
    fun getProducts() : List<Product> {
        val products = arrayListOf<Product>()

        products.add(Product(0.99, 1, "Coffee Sm", "2", ""))
        products.add(Product(1.22, 2, "Coffee Med", "1", ""))
        products.add(Product(1.54, 3, "Coffee Lg", "2", ""))
        products.add(Product(0.99, 4, "Lemon Poppy Seed Cake", "1", ""))
        products.add(Product(0.99, 5, "Banana", "2", ""))
        return products
    }
}