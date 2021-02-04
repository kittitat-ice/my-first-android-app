package com.example.myfirstapp.api.repository

import com.example.myfirstapp.api.RetrofitInstance
import com.example.myfirstapp.api.product.ProductListJson
import retrofit2.Response

class Repository {
  suspend fun getProductList(): Response<ProductListJson> {
    return RetrofitInstance.api.getProductList()
  }
}