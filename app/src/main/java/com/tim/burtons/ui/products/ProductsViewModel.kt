 package com.tim.burtons.ui.products

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tim.burtons.base.BaseViewModel
import com.tim.burtons.data.api.Resource
import com.tim.burtons.data.repo.ProductsRepository
import com.tim.burtons.model.Product
import com.tim.burtons.utils.Utils
import kotlinx.coroutines.Dispatchers

class ProductsViewModel @ViewModelInject constructor(private val productsRepository: ProductsRepository) : BaseViewModel() {


    /* add a branch */
    /* new line is added in the master */
    /* rebase practice */
    /* new lines are added */
    init {
        getProducts()
/*testing new bracch */
    }

    private var _products = arrayListOf<Product>()

    private var _productsLiveData = MutableLiveData<List<Product>>()

    val productsLiveData:LiveData<List<Product>> = _productsLiveData

    var searchText: String? = ""
        set(value) {
            field = value

            filterData()
        }

    private fun filterData() {

        if (searchText.isNullOrEmpty()){
            resetData()
        }

        val filteredProducts  = _products.filter { it.name?.contains(searchText!!, true) ?: false } as ArrayList<Product>
        _productsLiveData.value = filteredProducts
    }

    private fun resetData() {
        _productsLiveData.value = _products
    }


    private val productsObserver: Observer<Resource<List<Product>>> =
        Observer { t -> processResponse(t) }

    private fun processResponse(response: Resource<List<Product>>) {
        when (response.status) {
            Resource.Status.LOADING -> {
                setLoading()
            }

            Resource.Status.SUCCESS -> {
                setSuccess()
                _products = response.data as ArrayList<Product>
                _productsLiveData.value = _products
            }
            Resource.Status.ERROR -> {
                setError()
                error.value = response.apiError?.message ?: "Something went wrong."
            }
        }
    }


    private fun getProducts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
           emit(Resource.success(data = productsRepository.getProducts()))
        } catch (ex: Exception) {
            emit(Resource.success(data = Utils.getProducts()))
        }
    }.observeForever{
        productsObserver.onChanged(it as Resource<List<Product>>?)
    }

}