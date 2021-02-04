package com.example.myfirstapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.api.product.ProductListJson
import com.example.myfirstapp.api.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductListViewModel(private val repository: Repository): ViewModel() {
  val response: MutableLiveData<Response<ProductListJson>> = MutableLiveData()
  fun getProductList() {
    viewModelScope.launch {
      val res: Response<ProductListJson> = repository.getProductList()
      response.value = res
    }
  }
}