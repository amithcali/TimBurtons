package com.tim.burtons.data.repo

import com.tim.burtons.model.Product

interface ProductsRepository {
    suspend fun getProducts() : List<Product>
}