package com.tim.burtons.data.repo

import com.tim.burtons.data.api.APIService
import com.tim.burtons.model.Product
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val apiService: APIService) : ProductsRepository {
    override suspend fun getProducts(): List<Product> {
        return apiService.getUsers()
    }
}