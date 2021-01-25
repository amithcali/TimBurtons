package com.tim.burtons.di

import com.tim.burtons.data.api.APIService
import com.tim.burtons.data.repo.ProductsRepository
import com.tim.burtons.data.repo.ProductsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideAPIService(): APIService {
        return APIService.create()
    }

    @Singleton
    @Provides
    fun provideProductsRepository(): ProductsRepository {
        return ProductsRepositoryImpl(provideAPIService())
    }
}
