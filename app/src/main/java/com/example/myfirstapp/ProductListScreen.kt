package com.example.myfirstapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapp.api.repository.Repository
import com.example.myfirstapp.adapter.ProductListAdapter
import com.example.myfirstapp.databinding.ProductListScreenBinding
import com.example.myfirstapp.viewModel.ProductListViewModel
import com.example.myfirstapp.viewModelFactory.ProductListViewModelFactory
import kotlinx.coroutines.*

class ProductListScreen : Fragment() {

  private lateinit var navController: NavController
  private lateinit var activity: AppCompatActivity
  private var _binding: ProductListScreenBinding? = null
  private val binding get() = _binding!!
  private val scope = MainScope()
  private lateinit var viewModel: ProductListViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = ProductListScreenBinding.inflate(inflater, container, false)
    activity = (requireActivity() as AppCompatActivity)
    navController = Navigation.findNavController(activity, R.id.nav_host_fragment)
    // set title
    activity.supportActionBar?.title = navController.currentDestination?.label

    val repository = Repository()
    val viewModelFactory = ProductListViewModelFactory(repository)
    viewModel = ViewModelProvider(this, viewModelFactory).get(ProductListViewModel::class.java)

    binding.root.setOnRefreshListener { callAPI() }

    binding.overlay.visibility = View.VISIBLE
    callAPI()

    return binding.root
  }

  private fun callAPI() {
    viewModel.getProductList()
    viewModel.response.observe(viewLifecycleOwner, { res ->
      if(res.isSuccessful){
        Log.d("RESPONSE", res.body().toString())
        binding.productList.adapter = ProductListAdapter(res.body()!!)
        binding.productList.layoutManager = LinearLayoutManager(binding.root.context)
        binding.overlay.visibility = View.GONE
        binding.root.isRefreshing = false
      }else {
        Toast.makeText(context, getText(R.string.list_error), Toast.LENGTH_LONG).show()
        binding.root.isRefreshing = false
      }
    })
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}