package com.tim.burtons.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    internal val error: MutableLiveData<String> = MutableLiveData()
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val isError: MutableLiveData<Boolean> = MutableLiveData()
    private val isSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun getError(): LiveData<String> {
        return error
    }

    internal fun setSuccess() {
        isLoading.value = false
        isSuccess.value = true
        isError.value = false
    }

    internal fun setError() {
        isLoading.value = false
        isSuccess.value = false
        isError.value = true
    }

    internal fun setLoading() {
        isLoading.value = true
        isSuccess.value = false
        isError.value = false
    }
}