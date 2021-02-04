package com.example.myfirstapp.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfirstapp.api.repository.Repository
import com.example.myfirstapp.viewModel.ProductListViewModel

class ProductListViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return ProductListViewModel(repository) as T
  }
}