package com.example.myfirstapp.api

import com.example.myfirstapp.api.product.ProductListJson
import retrofit2.Response
import retrofit2.http.GET

interface ApiRequests {

  @GET("/users")
  suspend fun getProductList(): Response<ProductListJson>
}