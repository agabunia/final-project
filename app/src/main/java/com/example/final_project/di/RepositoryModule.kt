package com.example.final_project.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.final_project.data.common.HandleResponse
import com.example.final_project.data.local.dao.ProductDao
import com.example.final_project.data.local.repository.datastore.DataStoreRepositoryImpl
import com.example.final_project.data.local.repository.room.LocalProductRepositoryImpl
import com.example.final_project.data.remote.repository.home.CategoryListRepositoryImpl
import com.example.final_project.data.remote.repository.home.ProductByCategoryRepositoryImpl
import com.example.final_project.data.remote.repository.login.LoginRepositoryImpl
import com.example.final_project.data.remote.repository.product.ProductDetailedRepositoryImpl
import com.example.final_project.data.remote.repository.profile.GetProfileImageRepositoryImpl
import com.example.final_project.data.remote.repository.profile.ProfileChangeImageRepositoryImpl
import com.example.final_project.data.remote.repository.registration.RegistrationRepositoryImpl
import com.example.final_project.data.remote.repository.search.ProductRepositoryImpl
import com.example.final_project.data.remote.repository.search.ProductSearchRepositoryImpl
import com.example.final_project.data.remote.service.home.CategoryListService
import com.example.final_project.data.remote.service.home.ProductByCategoryService
import com.example.final_project.data.remote.service.product.ProductDetailedService
import com.example.final_project.data.remote.service.search.ProductSearchService
import com.example.final_project.data.remote.service.search.ProductService
import com.example.final_project.domain.local.repository.wishlist.LocalProductRepository
import com.example.final_project.domain.local.repository.datastore.DataStoreRepository
import com.example.final_project.domain.remote.repository.home.CategoryListRepository
import com.example.final_project.domain.remote.repository.home.ProductByCategoryRepository
import com.example.final_project.domain.remote.repository.login.LoginRepository
import com.example.final_project.domain.remote.repository.product.ProductDetailedRepository
import com.example.final_project.domain.remote.repository.profile.GetProfileImageRepository
import com.example.final_project.domain.remote.repository.profile.ProfileRepository
import com.example.final_project.domain.remote.repository.registration.RegistrationRepository
import com.example.final_project.domain.remote.repository.search.ProductRepository
import com.example.final_project.domain.remote.repository.search.ProductSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(impl: LoginRepositoryImpl): LoginRepository = impl

    @Provides
    @Singleton
    fun provideRegistrationRepository(impl: RegistrationRepositoryImpl): RegistrationRepository =
        impl

    @Provides
    @Singleton
    fun provideProfileRepository(impl: ProfileChangeImageRepositoryImpl): ProfileRepository =
        impl

    @Provides
    @Singleton
    fun provideGetProfileImageRepository(impl: GetProfileImageRepositoryImpl): GetProfileImageRepository =
        impl

    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>): DataStoreRepository {
        return DataStoreRepositoryImpl(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        handleResponse: HandleResponse,
        productService: ProductService
    ): ProductRepository {
        return ProductRepositoryImpl(
            handleResponse = handleResponse,
            productService = productService
        )
    }

    @Provides
    @Singleton
    fun provideProductSearchRepository(
        handleResponse: HandleResponse,
        productSearchService: ProductSearchService
    ): ProductSearchRepository {
        return ProductSearchRepositoryImpl(
            handleResponse = handleResponse,
            productSearchService = productSearchService
        )
    }

    @Provides
    @Singleton
    fun provideProductDetailedRepository(
        handleResponse: HandleResponse,
        productDetailedService: ProductDetailedService
    ): ProductDetailedRepository {
        return ProductDetailedRepositoryImpl(
            handleResponse = handleResponse,
            productDetailedService = productDetailedService
        )
    }

    @Provides
    @Singleton
    fun provideProductByCategoryRepository(
        handleResponse: HandleResponse,
        productByCategoryService: ProductByCategoryService
    ): ProductByCategoryRepository {
        return ProductByCategoryRepositoryImpl(
            handleResponse = handleResponse,
            productByCategoryService = productByCategoryService
        )
    }

    @Provides
    @Singleton
    fun provideCategoryListRepository(
        handleResponse: HandleResponse,
        categoryListService: CategoryListService
    ): CategoryListRepository {
        return CategoryListRepositoryImpl(
            handleResponse = handleResponse,
            categoryListService = categoryListService
        )
    }

    @Provides
    @Singleton
    fun provideLocalProductRepository(productDao: ProductDao): LocalProductRepository {
        return LocalProductRepositoryImpl(productDao = productDao)
    }

}
